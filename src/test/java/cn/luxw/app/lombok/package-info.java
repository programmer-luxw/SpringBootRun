
/**
 * https://blog.csdn.net/sunsfan/article/details/53542374
 * @Description: TODO
 * @author JULY
 * @date  2018年3月28日 
 * @version V1.0 
 * https://projectlombok.org/features/index.html
 */
package cn.luxw.app.lombok;
/*
另：@Data相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5个注解的合集

@NoArgsConstructor/@RequiredArgsConstructor /@AllArgsConstructor

这三个注解都是用在类上的，第一个和第三个都很好理解，就是为该类产生无参的构造方法和包含所有参数的构造方法，
第二个注解则使用类中所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法，
当然，和前面几个注解一样，成员变量都是非静态的，
另外，如果类中含有final修饰的成员变量，是无法使用@NoArgsConstructor注解的。 
 三个注解都可以指定生成的构造方法的访问权限，
 同时，第二个注解还可以用@RequiredArgsConstructor(staticName=”methodName”)的形式生成一个指定名称的静态方法，返回一个调用相应的构造方法产生的对象，下面来看一个生动鲜活的例子：
@RequiredArgsConstructor(staticName = "sunsfan")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Shape {
    private int x;
    @NonNull
    private double y;
    @NonNull
    private String name;
}1
2
3
4
5
6
7
8
9
10


实际效果相当于：
public class Shape {
    private int x;
    private double y;
    private String name;

    public Shape(){
    }

    protected Shape(int x,double y,String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Shape(double y,String name){
        this.y = y;
        this.name = name;
    }

    public static Shape sunsfan(double y,String name){
        return new Shape(y,name);
    }
}



*/