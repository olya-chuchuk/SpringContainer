package ua.rd.ioc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ApplicationContextTest {
    private Map<String, Map<String, Object>> beanDescriptions;

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
        beanDescriptions.put(beanName,
                new HashMap<String, Object>() {
                    {
                        put("type", TestBean.class);
                    }});
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
        beanDescriptions.put(beanName1,
                new HashMap<String, Object>() {
                    {
                        put("type", TestBean.class);
                    }});
        beanDescriptions.put(beanName2,
                new HashMap<String, Object>() {
                    {
                        put("type", TestBean.class);
                    }});
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
        beanDescriptions.put(beanName,
                new HashMap<String, Object>() {
                    {
                        put("type", TestBean.class);
                    }});
        Config config = new JavaMapConfig( beanDescriptions);
        Context context = new ApplicationContext(config);

        Object bean = context.getBean(beanName);

        assertNotNull(bean);
    }

    @Test
    public void getBeanWithOneBeanDefinition() throws Exception {
        String beanName = "First bean";
        Class<TestBean> beanType = TestBean.class;

        beanDescriptions.put(beanName,
                new HashMap<String, Object>() {{
                    put("type", beanType);
                }}
        );

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean = (TestBean) context.getBean(beanName);


        assertNotNull(bean);
    }

    @Test
    public void getBeanIsSingletonByDefault() throws Exception {
        String beanName = "First bean";
        Class<TestBean> beanType = TestBean.class;

        beanDescriptions.put(beanName,
                new HashMap<String, Object>() {{
                    put("type", beanType);
                }}
        );

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean1 = (TestBean) context.getBean(beanName);
        TestBean bean2 = (TestBean) context.getBean(beanName);

        assertSame(bean1, bean2);
    }

    @Test
    public void getBeanNotSameInstancesWithSameType() throws Exception {
        String beanName1 = "First bean";
        String beanName2 = "Second bean";
        beanDescriptions.put(beanName1,
                new HashMap<String, Object>() {
                    {
                        put("type", TestBean.class);
                    }});
        beanDescriptions.put(beanName2,
                new HashMap<String, Object>() {
                    {
                        put("type", TestBean.class);
                    }});
        Config config = new JavaMapConfig( beanDescriptions);
        Context context = new ApplicationContext(config);



        TestBean bean1 = (TestBean) context.getBean(beanName1);
        TestBean bean2 = (TestBean) context.getBean(beanName2);

        assertNotSame(bean1, bean2);
    }


    @Test
    public void getBeanIsPrototype() throws Exception {
        String beanName = "First bean";
        Class<TestBean> beanType = TestBean.class;

        beanDescriptions.put(beanName,
                new HashMap<String, Object>() {{
                    put("type", beanType);
                    put("isPrototype", true);
                }}
        );

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean1 = (TestBean) context.getBean(beanName);
        TestBean bean2 = (TestBean) context.getBean(beanName);

        assertNotSame(bean1, bean2);
    }

    @Test
    public void getBeanWithDependedBeans() throws Exception {
        beanDescriptions.put("testBean",
                new HashMap<String, Object>() {{
                    put("type", TestBean.class);
                    put("isPrototype", false);
                }}
        );
        beanDescriptions.put("testBeanWithConstructor",
                new HashMap<String, Object>() {{
                    put("type", TestBeanWithConstructor.class);
                    put("isPrototype", false);
                }}
        );

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBeanWithConstructor bean = (TestBeanWithConstructor) context.getBean("testBeanWithConstructor");

        assertNotNull(bean);
    }

    @Test
    public void getBeanWithDependedBeansAndTwoParams() throws Exception {
        beanDescriptions.put("testBean",
                new HashMap<String, Object>() {{
                    put("type", TestBean.class);
                    put("isPrototype", false);
                }}
        );
        beanDescriptions.put("testBeanWithConstructorTwoParams",
                new HashMap<String, Object>() {{
                    put("type", TestBeanWithConstructorTwoParams.class);
                    put("isPrototype", false);
                }}
        );

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBeanWithConstructorTwoParams bean =
                (TestBeanWithConstructorTwoParams) context.getBean("testBeanWithConstructorTwoParams");

        assertNotNull(bean);
    }

    @Test
    public void getBeanWithSeveralDependedBeansIsSingleton() throws Exception {
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>() {{
                    put("testBean",
                            new HashMap<String, Object>() {{
                                put("type", TestBean.class);
                                put("isPrototype", false);
                            }}
                    );
                    put("testBeanWithConstructorTwoParams",
                            new HashMap<String, Object>() {{
                                put("type", TestBeanWithConstructorTwoParams.class);
                                put("isPrototype", false);
                            }}
                    );
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBeanWithConstructorTwoParams bean
                = (TestBeanWithConstructorTwoParams) context.getBean("testBeanWithConstructorTwoParams");

        assertNotNull(bean);
        assertSame(bean.testBean1, bean.testBean2);
    }

    @Test
    public void getBeanWithSeveralDependedBeansIsPrototype() throws Exception {
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>() {{
                    put("testBean",
                            new HashMap<String, Object>() {{
                                put("type", TestBean.class);
                                put("isPrototype", true);
                            }}
                    );
                    put("testBeanWithConstructorTwoParams",
                            new HashMap<String, Object>() {{
                                put("type", TestBeanWithConstructorTwoParams.class);
                                put("isPrototype", false);
                            }}
                    );
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBeanWithConstructorTwoParams bean
                = (TestBeanWithConstructorTwoParams) context.getBean("testBeanWithConstructorTwoParams");

        assertNotNull(bean);
        assertNotSame(bean.testBean1, bean.testBean2);
    }

    @Test
    public void getBeanCallInitMethod() throws Exception {
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>() {{
                    put("testBean",
                            new HashMap<String, Object>() {{
                                put("type", TestBean.class);
                                put("isPrototype", true);
                            }}
                    );
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean = (TestBean) context.getBean("testBean");

        assertEquals("initialized", bean.initValue);
    }

    @Test
    public void getBeanCallMyPostConstruct() throws Exception {
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>() {{
                    put("testBean",
                            new HashMap<String, Object>() {{
                                put("type", TestBean.class);
                                put("isPrototype", true);
                            }}
                    );
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean = (TestBean) context.getBean("testBean");

        assertEquals("initialized", bean.initValue);
        assertEquals("initializedByPostConstruct", bean.postConstructValue);
    }

    @Test
    public void getBeanCallBenchmark() {
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>() {{
                    put("testBeanBenchmark",
                            new HashMap<String, Object>() {{
                                put("type", TestBeanBenchmark.class);
                                put("isPrototype", true);
                            }}
                    );
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        BenchmarkInterface bean = (BenchmarkInterface) context.getBean("testBeanBenchmark");

        System.out.println(bean.methodToBenchmark("abc"));
        bean.methodNoBenchmark();
    }


    public interface BenchmarkInterface {
        String methodToBenchmark(String str);
        void methodNoBenchmark();
    }

    static class TestBeanBenchmark implements BenchmarkInterface {
        @Benchmark(enable = true)
        @Override
        public String methodToBenchmark(String str) {
            return new StringBuilder(str).reverse().toString();
        }

        @Override
        @Benchmark(enable = false)
        public void methodNoBenchmark() {
            System.out.println("No benchmark");
        }
    }

    static class TestBean{

        String initValue;
        String postConstructValue;

        public void init(){
            initValue = "initialized";
        }

        @MyPostConstruct
        public void postConstruct(){
            initValue = "initializedByPostConstruct";
            postConstructValue = "initializedByPostConstruct";
        }
    }

    static class TestBeanWithConstructor {

        private TestBean testBean;

        TestBeanWithConstructor(TestBean testBean) {
            this.testBean = testBean;
        }
    }

    static class TestBeanWithConstructorTwoParams {

        private TestBean testBean1;
        private TestBean testBean2;

        public TestBeanWithConstructorTwoParams(TestBean testBean1, TestBean testBean2) {
            this.testBean1 = testBean1;
            this.testBean2 = testBean2;
        }
    }


}