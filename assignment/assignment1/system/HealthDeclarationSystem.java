package assignment1.system;

import assignment1.time.SUSTechTime;
import assignment1.people.AdministrativeStaff;
import assignment1.people.Faculty;
import assignment1.people.SUSTechMember;
import assignment1.people.Student;
import assignment1.record.HealthDeclarationForm;

import java.util.ArrayList;
import java.util.List;

public class HealthDeclarationSystem {
    private static final SUSTechTime SUSTechTime = new SUSTechTime();
    private static final List<SUSTechMember> SUSTechMembers = new ArrayList<>();
    private static final List<HealthDeclarationForm> SUSTechRecords = new ArrayList<>();

    public static SUSTechTime getSUSTechTime() {
        return SUSTechTime;
    }

    public static void addMembers(SUSTechMember m){
        SUSTechMembers.add(m);
    }

    public static void addRecord(HealthDeclarationForm r){
        SUSTechRecords.add(r);
    }

    public static List<HealthDeclarationForm> getSUSTechRecords() {
        return SUSTechRecords;
    }

    public static HealthDeclarationForm getStandardForm(SUSTechMember m) throws CloneNotSupportedException {
        return new HealthDeclarationForm(m, (assignment1.time.SUSTechTime) HealthDeclarationSystem.getSUSTechTime().clone());
    }

    public static void remindNotSubmitted(){
        if(!SUSTechTime.isRemindTime()){
            return;
        }

        for (SUSTechMember m:SUSTechMembers){
            if(!m.hasDeclared(SUSTechTime)){
                m.getNotSubmittedHint();
            }
        }
    }

    public static HealthDeclarationForm checkPrevious(SUSTechMember m){
        return null;
    }

    public static boolean hasChecked(SUSTechMember m,SUSTechTime t){
        return false;
    }

    public static void sendNotSubmittedToStaff(){
        if(!SUSTechTime.isSendTime()){
            return;
        }

        for (SUSTechMember m:SUSTechMembers){
            if(m instanceof AdministrativeStaff){
                ((AdministrativeStaff) m).getNotSubmitted();
            }
        }
    }

    public static List<HealthDeclarationForm> getRecordsByDepartment(String department){
        List<HealthDeclarationForm> result = new ArrayList<>();
        for (HealthDeclarationForm hdr: SUSTechRecords){
            if(hdr.getRecordTime().isSameDay(SUSTechTime) &&
                    (hdr.getMember() instanceof Student|| hdr.getMember() instanceof Faculty)
            && (hdr.getMember().getDepartment().equals(department))){
                result.add(hdr);
            }
        }

        return result;
    }

    public static SUSTechMember getMemberById(String id){
        if(id==null){
            return null;
        }
        for(SUSTechMember m:SUSTechMembers){
            if(id.equals(m.getID())){
                return m;
            }
        }

        return null;
    }
}
