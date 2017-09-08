package ua.rd.ioc;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class ApplicationContextTest {
    private Map<String, Class<?>> beanDescriptions;

    @Before
    public void init() {
        beanDescriptions = new HashMap<>();
    }
    @After
    public void cleanUp() {
        beanDescriptions = null;
    }
    @Test(expected = NoSuchBeanDefinitionException.class)
    public void getBean() throws Exception {
        Context context = new ApplicationContext();
        context.getBean("abc");
    }

    @Test
    public void getBeanDefinitionNamesWithEmptyContext() throws Exception {
        //given
        Context context = new ApplicationContext();

        //when
        String[] actual = context.getBeanDefinitionNames();

        //then
        String[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithOneBeanDefinition() throws Exception {
        String beanName = "FirstBean";
        //List<String> beanDescriptions = Arrays.asList(beanName);
        beanDescriptions.put(beanName, TestBean.class);
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {beanName};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithEmptyBeanDefinition() throws Exception {
        //Given
        Config config = new JavaMapConfig( beanDescriptions);
        Context context = new ApplicationContext(config);
        //When
        String[] actual = context.getBeanDefinitionNames();
        //Then
        String[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithSeveralBeanDefinitions() throws Exception {
        String beanName1 = "First bean";
        String beanName2 = "Second bean";
        //List<String> beanDescriptions = Arrays.asList(beanName1, beanName2);
        beanDescriptions.put(beanName1, TestBean.class);
        beanDescriptions.put(beanName2, TestBean.class);
        Config config = new JavaMapConfig( beanDescriptions);
        Context context = new ApplicationContext(config);

        Set<String> actual = new HashSet<>(Arrays.asList(context.getBeanDefinitionNames()));

        //Set<String> expected = new HashSet<>(Arrays.asList(beanName1, beanName2));
        Set<String> expected = new HashSet<String>() {
            {
                add(beanName1);
                add(beanName2);
            }
        };
        assertEquals(expected, actual);
    }

    @Test
    public void getBeanWithOneBeanDefinitionIsNotNull() throws Exception {
        String beanName = "First bean";
        //List<String> beanDescriptions = Arrays.asList(beanName);
        beanDescriptions.put(beanName, TestBean.class);
        Config config = new JavaMapConfig( beanDescriptions);
        Context context = new ApplicationContext(config);

        Object bean = context.getBean(beanName);

        assertNotNull(bean);
    }

    @Test
    public void getBeanWithOneBeanDefinition() throws Exception {
        String beanName = "First bean";
        Class<TestBean> beanType = TestBean.class;

        //List<String> beanDescriptions = Arrays.asList(beanName);
        Map<String, Class<?>> beanDescriptions = new HashMap<String, Class<?>>(){{
            put(beanName, beanType);
        }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean = (TestBean) context.getBean(beanName);


        assertNotNull(bean);
    }

    public static class TestBean {
        public TestBean() {
        }
    }

}