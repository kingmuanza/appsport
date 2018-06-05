package app.sport.utils;

import app.sport.entities.Discipline;
import app.sport.entities.IndividuDiscipline;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class DisciplineUtil {

    public boolean isInList(Discipline discipline, Set<IndividuDiscipline> individuDisciplines) {
        if (individuDisciplines != null && !individuDisciplines.isEmpty()) {
            for (IndividuDiscipline id : individuDisciplines) {
                if (Objects.equals(id.getDiscipline().getIddiscipline(), discipline.getIddiscipline())) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public static int calculateAge(Date birthDate, Date currentDate) {
        LocalDate b = Instant.ofEpochMilli(birthDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate c = Instant.ofEpochMilli(birthDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        
        if ((b != null) && (c != null)) {
            return Period.between(b, c).getYears();
        } else {
            return 0;
        }
    }
}
