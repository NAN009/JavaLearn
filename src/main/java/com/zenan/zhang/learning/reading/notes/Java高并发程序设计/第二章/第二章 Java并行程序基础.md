#### 2.2 线程的基本操作

##### 线程的基本状态

**NEW**:

**RUNNABLE**:

**BLOCKED**:

**WAITING**:

**TIMED_WAITING**:

**TERMINATED**:

##### 2.2.1 新建线程

线程有一个run()方法，调用start()方法就回新建一个线程并让这个线程执行run()方法。

直接调用run()方法并不会新建线程，只是在当前线程中执行run()方法。

##### 2.2.2 终止线程(已废弃)

stop()方法：直接终止线程，并释放线程所持有的锁，可能导致数据不一致(已废弃)

##### 2.2.3 线程中断

Thread.interrupt() 中断线程，设置中断标识位,并不立刻退出；如果只设置了中断状态，没有处理逻辑，终止不发生作用

Thread.isInterrupted() 判断是否被中断

Thread.interrupted() 判断是否被中断，并清除当前中断状态

Thread.sleep() 当前线程休眠若干时间，会抛出InterruptedException异常，InterruptedException在线程sleep()休眠时如果被中断就抛出

##### 2.2.4 等待(wait)、通知(notify)

wait() 由Object类提供，任何对象都可调用。

当对象实例上调用obj.wait()方法后，当前线程就会在这个对象上等待。即当前线程停止继续执行转为等待状态,直到其他线程调用obj.notify()方法为止。

当一个线程调用object.wait()，那么他就会进入object对象的等待队列，当object.notify()被调用，他就会从这个等待队里中随机选择一个线程，并将其唤醒；
如果调用notifyAll()将唤醒这个等待队列中所有的等待线程。

wait()、notify()必须包含在对于的synchronized语句中，也就是首先要获得目标对象的一个监视器


wait()、sleep()方法都可以让线程等待若干时间，wait()可以被唤醒，还会在同步块结束释放锁；sleep()方法不会释放任何资源;

**示例 SimpleWaitAndNotify**

##### 2.2.5 挂起(suspend)、继续执行(resume)线程(已废弃)

被挂起的线程必须等到resume()操作后，才能继续执行

废弃原因：被suspend()的线程导致线程暂停，不释放任何资源，导致任何线程想要访问被它暂用的锁时，都会被牵连，导致无法正常继续运行；
对于被suspend()的线程，状态是Runnable

**示例 BadSuspend**

##### 2.2.6 等待线程结束(join)、谦让(yield)

join() 等待其他线程执行完毕，才能继续执行

* public final void join() throws InterruptedException

  无限等待，阻塞当前线程，知道目标线程执行完毕

* public final synchronized void join(long millis) throws InterruptedException

  限时等待，超过时间就继续执行

  join()本质是让调用线程wait()在当前线程对象实例上，调用线程在当前线程对象上进行等待。
  当线程执行完成后，被等待的线程会在退出前调用notifyAll()通知所有的等待线程继续执行


yield() 一旦执行，会使当前线程让出CPU；与其他线程进行CPU资源争夺，争夺到则继续执行


#### 2.3 volatile

**volatile**:使变量在多个线程间可见，强制从公共堆中去的变量值，而不是从线程私有栈中取得变量值

但当多个线程同时修改时，仍存在问题

**示例 AccountingVol**

#### 2.4 线程组

activeCount() 获得获得线程的总数（估值）

list() 打印线程组所有线程信息

#### 2.5 守护线程（Daemon）

守护线程是系统守护者（如垃圾回收线程、JIT线程），必须在start()之前设置

用户线程是系统的工作线程，完成业务操作，如果用户线程

如果用户线程不存在，只有守护线程时，jvm就会自然退出

**示例DaemonDemo**

#### 2.6 线程的优先级

优先级有效范围 1-10

优先级越高在竞争资源时会更有优势

**示例 PriorityDemo**

#### 2.7 线程安全与synchronized

关键字 synchronized

**指定加锁对象**：对给定对象加锁，进入同步代码前要获得给定对象的锁

**直接作用于实例方法**：相当于对当前实例加锁，进入同步代码钱要获得当前实例的锁

**直接作用于静态方法**：相当于对当前类加锁，进入同步代码前要获得当前类的锁




