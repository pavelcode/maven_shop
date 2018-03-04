package com.cblue.shop.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import com.cblue.common.jedis.JedisClient;
import com.cblue.common.pojo.ResponseResult;
import com.cblue.common.utils.JsonUtils;
import com.cblue.shop.mapper.TbUserMapper;
import com.cblue.shop.pojo.TbUser;
import com.cblue.shop.pojo.TbUserExample;
import com.cblue.shop.pojo.TbUserExample.Criteria;
import com.cblue.shop.sso.service.LoginService;

public class LoginServiceImpl implements LoginService {

	/*业务逻辑：
	 * 1、判断用户和密码是否正确
	 * 2、如果不正确，返回登录失败
	 * 3、如果正确生成token。
	 * 4、把用户信息写入redis，key：token value：用户信息
	 * 5、设置Session的过期时间
	 * 6、把token返回
	 */
	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;  //默认过期时间30分钟
	@Override
	public ResponseResult userLogin(String username, String password) {
		// 1、判断用户和密码是否正确
				//根据用户名查询用户信息
				TbUserExample example = new TbUserExample();
				Criteria criteria = example.createCriteria();
				criteria.andUsernameEqualTo(username);
				//执行查询
				List<TbUser> list = userMapper.selectByExample(example);
				if (list == null || list.size() == 0) {
					//返回登录失败
					return ResponseResult.build(400, "用户名或密码错误");
				}
				//取用户信息
				TbUser user = list.get(0);
				//判断密码是否正确
				if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
					// 2、如果不正确，返回登录失败
					return ResponseResult.build(400, "用户名或密码错误");
				}
				// 3、如果正确生成token。
				String token = UUID.randomUUID().toString();
				// 4、把用户信息写入redis，key：token value：用户信息
				user.setPassword(null);
				jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(user));
				// 5、设置Session的过期时间
				jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
				// 6、把token返回
				 
				return ResponseResult.ok(token);
	}

}
