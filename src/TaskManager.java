package src;// Дорогой, я_из_будущего! Пожалуйста, прости меня за этот код.

import java.util.Scanner;

public class TaskManager {
    protected Task[] tasks = new Task[5];
    static int taskCount = 0; //счетчик количества всех задач, ввел для упрощения проверок
    private static final String[] menuMain = {"-Меню-", "1. Создать задачу", "2. Удалить задачу", "3. Посмотреть все задачи", "4. Выход"};

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        //вывод строк главного меню из массива
        for (String str : menuMain) {
            System.out.println(str);
        }
        System.out.print("Введите цифру для перехода: ");
        try {
            int numberMenu = scanner.nextInt();
            Scanner scanner2 = new Scanner(System.in);
            switch (numberMenu) {
                case 1:
                        /* Проверка, если массив задач переполнен. Не стал делать расширяемый массив. Это заняло бы больше
                        времени. В теории возможно смогу сделать */
                    if (taskCount == tasks.length) {
                        System.out.println("\nСписок переполнен. Удалите старые задачи!");
                        menuBack();
                    }
                    System.out.print("Введите имя задачи: ");
                    String taskName = scanner2.nextLine();
                    // проверка, есть ли задача с похожим названием в списке
                    for (Task value : tasks) {
                        if (value != null && value.getName().equals(taskName)) {
                            System.out.println("(" + taskName + ") задача с таким названием уже существует. Вы не можете добавить ее еще раз!");
                            menuBack();
                        }
                    }
                    System.out.print("Введите описание задачи: ");
                    String taskDescription = scanner2.nextLine();
                    Task task = new Task(taskName, taskDescription);
                    addTask(task);
                case 2:
                    //проверка, на наличии хотя бы одной задачи в списке
                    if (taskCount == 0) {
                        System.out.println("\nЗадач в списке нет. Добавьте задачу и вернитесь обратно");
                        menuBack();
                    } else {
                        // если есть задача в списке, программа выводит соообщение
                        System.out.print("Введите имя задачи, которую вы хотите удалить: ");
                        String name = scanner2.nextLine();
                        removeTask(name);
                    }
                case 3:
                    viewTasks();
                case 4:
                    System.exit(0);
                default:
                    //если пользователь ввел неподходящую цифру
                    System.out.println("Ошибка, попробуйте еще раз!\n");
                    showMainMenu();
            }
        } catch (Exception e) {
            // если пользователь ввел что то другое, а не цифру
            System.out.println("Ошибка, попробуйте еще раз!\n");
            showMainMenu();
        }
    }

    public void addTask(Task task) {
        // поиск первой пустой ячейки в массиве и добавление в него объекта класса
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                System.out.printf("Успех! Задача %s добавлена в список!\n", tasks[i].getName());
                taskCount++;
                menuBack();
            }
        }
    }

    public void removeTask(String name) {
        // поиск и удаление объекта в массиве, с похожим именем.
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].getName().equals(name)) {
                tasks[i] = null;
                System.out.printf("Задача (%s) была найдена и удалена\n", name);
                taskCount--;
                menuBack();
                break;
            }
        }
        //если в массиве нет объекта с похожим именем
        System.out.printf("(%s) задача не найдена!\n", name);
        menuBack();
    }


    public void viewTasks() {
        //проверка, на наличии хотя бы одной задачи в списке
        if (taskCount == 0) {
            System.out.println("\nВсего: " + taskCount + " задач. \nCоздайте новые задачи и вернитесь обратно");
            menuBack();
        } else {
            System.out.println("\nВсего: " + taskCount + " задач.\nСписок всех ваших задач: ");
            for (int i = 0; i <= tasks.length - 1; i++) {
                if (tasks[i] != null) {
                    System.out.println(tasks[i].toString());
                }
            }
            menuBack();
        }
    }

    public void menuBack() {
        // переход обратно в главное меню
        System.out.print("\nЛюбой текст для перехода в меню: ");
        Scanner sc = new Scanner(System.in);
        sc.next();
        showMainMenu();
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.showMainMenu();
    }
}

