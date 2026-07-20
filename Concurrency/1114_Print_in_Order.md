# Question  
```
Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads.
Thread A will call first(), thread B will call second(), and thread C will call third().
 Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().

Note:

We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seem to imply the ordering.
The input format you see is mainly to ensure our tests' comprehensiveness.
```
# Approach  
1. We define two semaphores for printing `second` and `third` initializing them with value `0`;
2. After printing first we release the semaphore for printing 2 and after printing second we release semaphore for printing third.
# Code    
```
class Foo {
public:
    counting_semaphore<1> sem2{0};
    counting_semaphore<1> sem3{0};
    Foo() {
    }

    void first(function<void()> printFirst) {
        printFirst();
        sem2.release();
    }

    void second(function<void()> printSecond) {
        sem2.acquire();
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        sem3.release();
    }

    void third(function<void()> printThird) {
        sem3.acquire();
        // printThird() outputs "third". Do not change or remove this line.
        printThird();
    }
};
```
