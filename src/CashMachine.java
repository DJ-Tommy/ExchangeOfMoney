import java.util.ArrayList;
import java.util.List;
/* Логика работы
Узнаем сколько максимум монет каждого номинала может быть в заданной сумме и записываем
это в массив длиной в количество монет. Узнаем из этого максимальное число.
Создаем двумерный массив с вариантами по каждой монете от максимального числа до конца
массива с шагом -1 (будут в том числе и отрицательные числа)
Создаем массив для перебора всех вариантов в котором храним только номера строк по каждой
монете.
Далее начинаем перебирать, начиная с первой позиции. Когда перваяпозиция доходит до максимальной,
то эта позиция становится нулем, следующая увеличивается на 1, но если и она достигла максимума,
то обнулется и следующая увеличивается на 1 и т.д. После того, как следующая увеличилась, курсор
опять идет на нулевую позицию и так в режиме рекурсии перебираются все возможные варианты из двумерного
массива.
Каждый вариант записывается в массив и проверяется, что в нем нет отрицательных значений и сумма равна
заданному для размена значению. В этом случае массив добавляется в результаты.
И так происходит, пока не переберутся все возможные варианты.
*/
public class CashMachine {
    private int note;               // получаем значение суммы ддя размена
    private int maxNumberOfCoin;                // максимальное количество монет при размене самой мелкой монетой
    private int [] maxAmountForEachCoin;             // массив с максимальным количеством монет по каждому номиналу
    private int [][] variants;      // двумерный массив со всеми возможными вариантами по количеству монет
    // Сумма - 10  монеты   2   5   10 Получится массив
    //  5   2   1
    //  4   1   0
    //  3   0   -1
    //  2   -1  -2
    //  1   -2  -3
    //  0   -3  -4
    private boolean status = true;  // для статуса цикла while
    private int numberOfTypesOfCoin;              // количество монет
    private int count = 0;         // номер монеты для перебора
    private int [] currentVariant;             // массив с набором вариантов монет
    private int[] curentPositionForEachCoin;               // массив для хранения позиции текущего варианта для каждой монеты
    private boolean statusNegativeValue = true;   // для проверки отрицательных значений в массиве с вариантами
    private List<Integer> currentList = new ArrayList<>(); // список монет, подходщий под условие
    private List<List<Integer>> finalList = new ArrayList<>(); // Полный Список всех возможных вариантов
    private final int[] values;     // массив с номиналами монет

    public CashMachine(final int[] values) {
        this.values = values;
    }

    public List<List<Integer>> exchange(int note) {
        this.note = note;
        maxNumberOfCoin = 0;
        numberOfTypesOfCoin = values.length;
        currentVariant = new int[numberOfTypesOfCoin];
        maxAmountForEachCoin = new int[numberOfTypesOfCoin];
        curentPositionForEachCoin = new int[numberOfTypesOfCoin];
        for (int i = 0; i < numberOfTypesOfCoin; i++) {
            maxAmountForEachCoin[i] = note / values[i];
            if (maxNumberOfCoin < maxAmountForEachCoin[i]) {
                maxNumberOfCoin = maxAmountForEachCoin[i] + 1;
            }
        }
        variants = new int[numberOfTypesOfCoin][maxNumberOfCoin];
        for (int x = 0; x < numberOfTypesOfCoin; x++) {
            for (int y = 0; y < maxNumberOfCoin; y++) {
                variants[x][y] = maxAmountForEachCoin[x] - y;
            }
        }
        while (status) {
            statusNegativeValue = true;
            int summ = 0;
            for (int i = 0; i < numberOfTypesOfCoin; i++) {
                currentVariant[i] = variants[i][curentPositionForEachCoin[i]];
                summ += currentVariant[i] * values [i];
                if (currentVariant[i] < 0) {
                    statusNegativeValue = false;
                }
            }
            if (statusNegativeValue && summ == note) {
                currentList = new ArrayList<>();
                for (int i = 0; i < numberOfTypesOfCoin; i++){
                    for (int q = 0; q < currentVariant[i]; q++) {
                        currentList.add(values[i]);
                    }
                }
                finalList.add(new ArrayList<>(currentList));
            }
            currentList.clear();
            boolean chek = true;
            count = 0;
            while (chek) {
                if (count >= numberOfTypesOfCoin) {
                    chek = false;
                    break;
                }
                curentPositionForEachCoin[count]++;
                if (curentPositionForEachCoin[count] == maxNumberOfCoin) {
                    curentPositionForEachCoin[count] = 0;
                    count++;
                } else {
                    chek = false;
                }
            }
            if (count >= numberOfTypesOfCoin) {
                status = false;
            }
        }
        return finalList;
    }
}