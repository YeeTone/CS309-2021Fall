package assignment1.record;

import assignment1.system.HealthDeclarationSystem;
import assignment1.time.SUSTechTime;
import assignment1.people.SUSTechMember;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HealthDeclarationForm {
    private final SUSTechMember member;
    private final SUSTechTime recordTime;
    private final Map<String,String> records;
    private static final Set<String> types = new HashSet<>();

    public HealthDeclarationForm(SUSTechMember member, SUSTechTime recordTime){
        this.member = member;
        this.recordTime = recordTime;
        this.records = new HashMap<>();
        for (String s:types){
            records.put(s,null);
        }
    }

    public static void addRecordType(String t){
        types.add(t);
    }

    public static void removeRecordTypes(String t){
        types.remove(t);
    }

    public static void changeRecordTypes(String s,String t){
        removeRecordTypes(s);
        addRecordType(t);
    }

    public void finishInput(){
        records.replaceAll((s, v) -> s);
    }

    public SUSTechTime getRecordTime() {
        return recordTime;
    }

    public SUSTechMember getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "HealthDeclarationForm{" +
                "member=" + member +
                ", recordTime=" + recordTime +
                ", records=" + records +
                '}';
    }
}
