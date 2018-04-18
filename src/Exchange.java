import java.util.List;
/*
* Задание: необходимо вывести все возможные варианты размена суммы note
* на заданный массив видов монет.
*
*
* */
public class Exchange {

        public static List<List<Integer>> a;

        public static void main(String[] args) {
            CashMachine obmen = new CashMachine(new int[]{1, 2, 10, 5});
            a = obmen.exchange(15);
            System.out.println("You have " + a.size() + " variants");
            System.out.println(a);
        }
    }



