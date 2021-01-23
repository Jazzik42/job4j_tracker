package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete Item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int item=input.askInt("Enter Id:");
        if(tracker.delete(item)){
            System.out.println("The application has been deleted.");
        }else{
            System.out.println("There is no application with this id.");
        }

        return true;
    }

}

