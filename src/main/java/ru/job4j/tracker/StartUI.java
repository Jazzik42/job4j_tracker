package ru.job4j.tracker;


import javax.security.auth.login.CredentialException;

public class StartUI {
    public final Output output;

    public StartUI(Output output) {
        this.output = output;
    }

public static void createItem(Input input, Tracker tracker){
        System.out.println("=== Create a new Item ====");
        String itemName1=input.askStr("Enter name:");
        Item item1=new Item(itemName1);
        tracker.add(item1);
        }

public static void allItems(Input input, Tracker tracker) {
        System.out.println("=== All items ===");
        for (Item items : tracker.findAll()){
        System.out.println(items.getName()+" "+items.getId());
        }
        }

public static void editItem(Input input, Tracker tracker) {
    System.out.println("=== Edit item ===");
    Item item2 = new Item();
    String itemName2 = input.askStr("Enter name:");
    int itemId2 = input.askInt("Enter id:");
    item2.setName(itemName2);
    System.out.println(tracker.replace(itemId2, item2) ? "The application has been replaced."
            : "There is no application with this id.");
}
public static void deleteItem(Input input, Tracker tracker){
        System.out.println("=== Delete item ===");
        int itemId3=input.askInt("Enter Id:");
        if(tracker.delete(itemId3)){
        System.out.println("The application has been deleted.");
        }else{
        System.out.println("There is no application with this id.");
        }
        }

public static void findItemById(Input input, Tracker tracker){
        System.out.println("=== Find item by Id ===");
        int itemId4 = input.askInt("Enter Id:");
        Item item4 = tracker.findById(itemId4);
        if (item4 != null) {
        System.out.println("Item Name:" + item4.getName());
        } else {
        System.out.println("There is no application with this id.");
        }
        }

public static void findItemByName(Input input, Tracker tracker){
        System.out.println("=== Find item by Name ===");
        String itemName5=input.askStr("Enter name:");
        Item[]itemArr=tracker.findByName(itemName5);
        if(itemArr.length != 0){
        for(Item items:itemArr){
        System.out.println("Item Id:"+items.getId());
        }
        }else{
        System.out.println("There are no applications with this name.");
        }
        }

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.length) {
                output.println("Wrong input, you can select: 0 .. " + (actions.length - 1));
                continue;
            }
            UserAction action = actions[select];
            run = action.execute(input, tracker);
            }
        }

    private void showMenu(UserAction[] actions) {
        output.println("Menu:");
        for (int i = 0; i < actions.length; i++) {
            output.println(i + "." + actions[i].name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(output), new DeleteAction(output), new FindAllAction(output),
        new FindByIdAction(output), new FindByNameAction(output), new ReplaceAction(output), new ExitProgramAction(output)};
        new StartUI(output).init(input, tracker, actions);

    }














}

