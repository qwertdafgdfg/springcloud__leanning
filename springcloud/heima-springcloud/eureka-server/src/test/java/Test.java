import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author: liYuan
 * @Title: Test
 * @ProjectName: springcloud
 * @Description:
 * @date: 2022/7/7 11:03
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Test {
    @Autowired
    private RestTemplate restTemplate;

//    @Test
//    public void test(){
////如果要测试需要启动spring boot项目，以便获取数据
//        String url = "http://localhost/user/8";
//        User user = restTemplate.getForObject(url, User.class);
//        System.out.println(user);
//    }
}
