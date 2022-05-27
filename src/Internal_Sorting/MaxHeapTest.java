package Internal_Sorting;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MaxHeapTest {
    MaxHeap<Student> students;
    MaxHeap<Integer> intheap;
    MaxHeap<Double> doubleheap;
    MaxHeap<String> otherCollects;
    MaxHeap<Student> studentsMath;

    @BeforeTest
    public void test_initHeap() {
        int[] nums1 = {-1, 2, 1, 5, 8, -45, 46, -4};
        double[] nums2 = {0.2, 5.6, 4.5, 3.3, 13.2, 4.0, 34.33, 11123};
        List<String> strlist = Arrays.asList(new String[]{"baidu", "tencent", "netease"});
        students = new MaxHeap<>();
        intheap = new MaxHeap<>(nums1);
        doubleheap = new MaxHeap<>(nums2);
        otherCollects = new MaxHeap<>(strlist);
//        System.out.println(otherCollects);
        studentsMath=new MaxHeap<>(new StudentMathComparator());
    }
    @Test
    public void test_add(){
        Student s1=new Student("张三",86,75);
        Student s2=new Student("李四",60,59.5);
        Student s3=new Student("王五",82,80);
        Student s4=new Student("赵六",95,96);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        System.out.println(Arrays.toString(students.getheap()));
        studentsMath.add(s1);
        studentsMath.add(s2);
        studentsMath.add(s3);
        studentsMath.add(s4);
        System.out.println(Arrays.toString(studentsMath.getheap()));
        doubleheap.add(45.1);
        doubleheap.add(48.0);
        doubleheap.add(75.1);
        System.out.println(doubleheap);
    }

    @Test
    public void testsort() {
        Student s1=new Student("张三",86,75);
        Student s2=new Student("李四",60,59.5);
        Student s3=new Student("王五",82,80);
        Student s4=new Student("赵六",95,96);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        studentsMath.add(s1);
        studentsMath.add(s2);
        studentsMath.add(s3);
        studentsMath.add(s4);
        int[] ans={-1, 2, 1, 5, 8, -45, 46, -4};
        Arrays.sort(ans);
        Assert.assertEquals(intheap.sort(),ans);
        double[] nums2 = {0.2, 5.6, 4.5, 3.3, 13.2, 4.0, 34.33, 11123};
        Arrays.sort(nums2);
        Assert.assertEquals(doubleheap.sort(),nums2);
        Assert.assertEquals(otherCollects.sort(),new String[]{"baidu", "netease", "tencent"});
        Student[] ans1=new Student[]{s2,s1,s3,s4};
        Assert.assertEquals(students.sort(),ans1);
        Student[] ans2={s2,s3,s1,s4};
        Assert.assertEquals(studentsMath.sort(),ans2);
        studentsMath.BuildHeap();
        System.out.println(Arrays.toString(studentsMath.sort()));
    }
}
