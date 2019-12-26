package chapter_1_stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/*
猫狗队列
【题目】
宠物、狗和猫的类如下：
public class Pet {
private String type;
public Pet(String type) {
this.type = type;
}
public String getPetType() {
return this.type;
}
}

public class Dog extends Pet {
public Dog() {
super("dog");
}
}

public class Cat extends Pet {
public Cat() {
super("cat");
}
}
实现一种狗猫队列的结构，要求如下：
•	用户可以调用add方法将cat类实例/dog类的实例放入队列中；
•	用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
•	用户可以调用pollDog方法，将队列中dog类的实例按腰进队列的先后顺序依次弹出；
•	用户可以调用pollCat方法,将队列中cat类的实例按熊进队列的先后顺序依次弹出；
•	用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例；
•	用户可以调用isDogEmpty方法,检查队列中是否有dog奏的实例；
•	用户可以调用isCatEmpty方法，检査队列中是否有cat类的实例。

【难度】
士 ★☆☆☆

【解答】
本题考査实现特殊数据结构的能力以及针对特殊功能的算法设计能力。
本题为开放类型的面试题，希望读者能有自己的实现，
在这里列出几种常见的设计错误：
・cat队列只放cat实例，dog队列只放dog实例，再用一个总队列放所有的实例。
销误原因：cat、dog以及总队列的更新问题。
•	用哈希表，key表示一个cat实例或dog实例，value表示这个实例进队列的次序。
错误原因：不能支持一个实例多次进队列的功能需求，因为哈希表的key只能对应一个value值。
•	将用户原有的cat或dog类改写，加一个计数项来表示某一个实例进队列的时间。
错误原因：不能擅自改变用户的类结构。
本题实现将不同的实例盖上时间戳的方法，但是又不能改变用户本身的类，所以定义
一个新的类，具体实现请参看如下的PetEnterQueue类。
PetEnterQueue类在构造时，pet是用户原有的实例，count就是这个实例的时间戳。

【学习程度】
理解了逻辑。手写问题应该不大。速度+熟练度 有待商榷。。。

 */

public class Problem_04_DogCatQueue {

    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue {
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return this.pet;
        }

        public long getCount() {
            return this.count;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

    public static class DogCatQueue {
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;

        public DogCatQueue() {
            this.dogQ = new LinkedList<PetEnterQueue>();
            this.catQ = new LinkedList<PetEnterQueue>();
            this.count = 0;
        }

        public void add(Pet pet) {
            if (pet.getPetType().equals("dog")) {
                this.dogQ.add(new PetEnterQueue(pet, this.count++));
            } else if (pet.getPetType().equals("cat")) {
                this.catQ.add(new PetEnterQueue(pet, this.count++));
            } else {
                //作者。 RuntimeException 只要不catch，运行时自动抛给上次调用方。【不用处理异常检测，简化代码编写！】代码小技巧
                throw new RuntimeException("err, not dog or cat");
                //我。Exception 编译时异常，语法需要 catch 或throw ,不便于代码编写。
//                throw new Exception("err, not dog or cat");
            }
        }

        public Pet pollAll() {
            if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
                if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
//                    先进先出，count小的先取出
                    return this.dogQ.poll().getPet();
                } else {
                    return this.catQ.poll().getPet();
                }
            } else if (!this.dogQ.isEmpty()) {
                return this.dogQ.poll().getPet();
            } else if (!this.catQ.isEmpty()) {
                return this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        public Dog pollDog() {
            if (!this.isDogQueueEmpty()) {
                return (Dog) this.dogQ.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat() {
            if (!this.isCatQueueEmpty()) {
                return (Cat) this.catQ.poll().getPet();
            } else{
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isEmpty() {
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
        }

        public boolean isDogQueueEmpty() {
            return this.dogQ.isEmpty();
        }

        public boolean isCatQueueEmpty() {
            return this.catQ.isEmpty();
        }

    }

    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogQueueEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }

}
