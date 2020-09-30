

public class test1 {

       static volatile char c = 'A';
        static Object ABC = new Object();

        static class WaitNotifyClass implements Runnable {
            private char currentLetter;
            private char nextLetter;

            public WaitNotifyClass(char currentLetter, char nextLetter) {
                this.currentLetter = currentLetter;
                this.nextLetter = nextLetter;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (ABC) {
                        try {
                            while (c != currentLetter)
                                ABC.wait();
                            System.out.print(currentLetter);
                            c = nextLetter;
                            ABC.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        public static void main(String[] args) {
            System.out.println("1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.");
            System.out.println ("Результат: ");
            new Thread(new WaitNotifyClass('A', 'B')).start();
            new Thread(new WaitNotifyClass('B', 'C')).start();
            new Thread(new WaitNotifyClass('C', 'A')).start();
        }
    }
