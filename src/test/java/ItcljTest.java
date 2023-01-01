import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itclj.ItcljApplication;
import com.itclj.dao.bean.User;
import com.itclj.dao.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItcljApplication.class)
public class ItcljTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 全量查询
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(obj -> {
            System.out.println(JSON.toJSONString(obj));
        });
    }

    /**
     * 按email查询
     */
    @Test
    public void testQueryByEmail() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email", "itclj960@test.com");
        List<User> userList = userMapper.selectList(userQueryWrapper);
        userList.forEach(obj -> System.out.println(JSON.toJSONString(obj)));
    }

    /**
     * 分页查询
     */
    @Test
    public void testQueryPage() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.ge("id", 20);//id >= 20

        Page<User> userPage = new Page<>(1, 4);
        Page<User> userResult = userMapper.selectPage(userPage, userQueryWrapper);
        System.out.println(JSON.toJSONString(userResult));
    }

    /**
     * 批量添加
     */
    @Test
    public void testAdd() {
        for (int n = 0; n < 1000; n++) {
            User user = new User();
            user.setAge(n);
            user.setEmail("itclj" + n + "@test.com");
            user.setName("itclj" + n);
            int num = userMapper.insert(user);
            System.out.println(num + JSON.toJSONString(user));
        }
    }

    /**
     * 按id删除
     */
    @Test
    public void testDel() {
        Integer id = 6;
        int num = userMapper.deleteById(id);
        System.out.println(num);
    }

    /**
     * 按id更新
     */
    @Test
    public void testEdit() {
        User user = new User();
        user.setId(7L);
        user.setName("李四");
        user.setEmail("lisi@itclj.com");
        int num = userMapper.updateById(user);
        System.out.println(num);
    }
}
