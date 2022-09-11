import com.alibaba.fastjson.JSON;
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

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(obj -> {
            System.out.println(JSON.toJSONString(obj));
        });
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setAge(1);
        user.setEmail("itclj003@test.com");
        user.setName("itclj3");
        int num = userMapper.insert(user);
        System.out.println(num + JSON.toJSONString(user));

    }

    @Test
    public void testDel() {
        Integer id = 6;
        int num = userMapper.deleteById(id);
        System.out.println(num);
    }

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
