package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AttachmentSort {
    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("image 1", 100),
                new Attachment("image 2", 34),
                new Attachment("image 3", 13)
        );
        Comparator<Attachment> compSize = new Comparator<>() {
            @Override
            public int compare(Attachment a1, Attachment a2) {
                return a1.getSize() - a2.getSize();
            }
        };
        attachments.sort(compSize);
        System.out.println(attachments);
        Comparator<Attachment> compName = new Comparator<>() {
            @Override
            public int compare(Attachment o1, Attachment o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        attachments.sort(compName);
        System.out.println(attachments);
    }
}
