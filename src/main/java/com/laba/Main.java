package com.laba;


import com.laba.task1.Faculty;
import com.laba.task1.Group;
import com.laba.task1.Speciality;
import com.laba.task1.Student;
import com.laba.task2.json.SpecialityJsonConverter;
import com.laba.task2.txt.FacultyTxtConverter;
import com.laba.task2.xml.StudentXmlConverter;
import com.laba.task3.FacultyCollection;
import com.laba.task3.GroupCollection;
import com.laba.task3.SpecialityCollection;
import com.laba.task3.StudentCollection;
import com.laba.task3.builder.FacultyBuilder;
import com.laba.task3.builder.GroupBuilder;
import com.laba.task3.builder.SpecialityBuilder;
import com.laba.task3.builder.StudentBuilder;
import com.laba.task5.Db;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static PrintStream out = new PrintStream(System.out, true, Charset.forName("utf-8")); // true = autoflush
    static PrintStream out1251 = new PrintStream(System.out, true, Charset.forName("cp1251")); // true = autoflush


    public static void main(String[] arg) throws UnsupportedEncodingException {




        List<Faculty> faculties = new ArrayList<>();
        List<Speciality> specialities = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        createData(faculties,specialities,groups,students);


        printFaculty(faculties);
        printSpeciality(specialities);
        printGroup(groups);
        printStudent(students);

        // демонстрація роботи з колекціями
        demoCollectons(faculties,specialities,groups,students);

        // демонстрація роботи конверторів
        demoConverter(faculties,specialities,groups,students);

        // демонстрація groupStudentsByFaculty
        out1251.println("\nЗгрупуємо студентів по факультету та виведемо їх кількість");
        groupStudentsByFaculty(students).forEach((f,s)->{
            out1251.printf("Факультет %s має %d студентів\n",cp1251(f.getName()),s.size());
        });

        // створення таблиць в базі даних
        out1251.println("\nСтворимо таблці в базі даних");
        Db db =new Db();
        db.initTables();
        db.showTables();

    }


    public static String getString(String promt){
       out1251.print(promt);
        String temp = scanner.nextLine();
        return   new String(temp.getBytes(Charset.defaultCharset()),Charset.forName("utf8"));
    }
    public static void createData(List<Faculty> faculties,
                                  List<Speciality> specialities,
                                  List<Group> groups,
                                  List<Student> students
                                  ){
        int fSize = getInt("Скільки факультетів будемо створювати? ");
        for (int i = 0; i < fSize; i++) {
            out1251.printf("Створення %d факультету \n", i + 1);
            Faculty faculty = createFaculty();
            faculties.add(faculty);
            int sSize = getInt("Скільки спеціальностей  будемо створювати? ");
            for (int j = 0; j < sSize; j++) {
                out1251.printf("Створення %d спеціальності для факультету \"%s\"\n", j + 1,cp1251(faculty.getName()));
                Speciality speciality = createSpeciality(faculty);
                specialities.add(speciality);
                int gSize = getInt("Скільки груп будемо створювати ");
                for (int g = 0; g < gSize; g++) {
                    out1251.printf("Створення %d групи для спеціальності  \"%s\"\n", g + 1,
                            cp1251(speciality.getName()));
                    Group group = createGroup(speciality);
                    groups.add(group);
                    int stSize = getInt("Скільки студентів додамо? ");
                    for (int s = 0; s < stSize; s++) {
                        System.out.printf("Створення студента %d  для групи \"%s\"\n ", s + 1,
                                cp1251(group.getCode()));
                        Student student = createStudent(group);
                        students.add(student);
                    }
                }
            }
            System.out.println("******************************************");
        }
    }
    public static void demoCollectons(List<Faculty> faculties,
                                List<Speciality> specialities,
                                List<Group> groups,
                                List<Student> students){
        out1251.println("\nВідосортуємо факультети по коду");
        faculties = FacultyCollection.sort(faculties);
        printFaculty(faculties);
        out1251.println("\nВідсортуємо спеціальності по назві (Stream API) ");
        specialities = SpecialityCollection.sortByName8(specialities);
        printSpeciality(specialities);
        out1251.println("\nВідсортуємо групи по курсу");
        groups = GroupCollection.sortByCourse(groups);
        printGroup(groups);
        out1251.println("\nВідсортуємо студентів по номеру залікової книжки ");
        students = StudentCollection.sortByCreditBook8(students);
        printStudent(students);
        out1251.println("\nВиведемо всіх деканів факультетів ");
        FacultyCollection.getAllDeans(faculties).forEach(out::println);
        out1251.println("\nЗгрупуємо спеціальності по факультету");
        Map<Faculty, List<Speciality>> map = SpecialityCollection.groupByGroup8(specialities);
        map.entrySet().forEach((e)->{
            out1251.printf("Факультет %s має %d спецільностей\n",cp1251(e.getKey().getName()),
                    e.getValue().size());
        });
        out1251.println("\nЗгрупуємо групи по спеціальності");
        Map<Speciality, List<Group>> mapGroup = GroupCollection.groupByGroup(groups);
        mapGroup.entrySet().forEach((e)->{
            out1251.printf("Спеціальнісь %s має %d груп(и)\n",cp1251(e.getKey().getName()),
                    e.getValue().size());
        });

    }

    public static String cp1251(String utf8){
        return new String(utf8.getBytes(Charset.forName("utf8")),Charset.forName("cp1251"));

    }

    public static void demoConverter(List<Faculty> faculties,
                                     List<Speciality> specialities,
                                     List<Group> groups,
                                     List<Student> students){
        out1251.println("\nЗбережемо першого студента до xml файлу");
        String fileName = "student.xml";
        StudentXmlConverter studentXmlConverter = new StudentXmlConverter();
        studentXmlConverter.serializeTo(students.get(0),new File(fileName));
        printFile(fileName);
        out1251.println("\nЗчитаємо студента з xml файлу");
        Student student = studentXmlConverter.deserializeFrom(new File(fileName));
        out.println(student);
        out1251.println("\nЗбережемо першу спеціальність до json файлу");
        SpecialityJsonConverter specialityJsonConverter = new SpecialityJsonConverter();
        fileName = "speciality.json";
        specialityJsonConverter.serializeTo(specialities.get(0),new File(fileName));
        printFile(fileName);
        out1251.println("\nЗчитаємо спецільність з json файлу");
        Speciality speciality = specialityJsonConverter.deserializeFrom(new File(fileName));
        out.println(speciality);
        out1251.println("\nЗапишемо перший факультет до txt файлу ");
        FacultyTxtConverter facultyTxtConverter = new FacultyTxtConverter();
        fileName ="faculty.txt";
        facultyTxtConverter.serializeTo(faculties.get(0),new File(fileName));
        out1251.println("\nЗчитаємо факультет з текстового файлу");
        Faculty faculty = facultyTxtConverter.deserializeFrom(new File(fileName));
        out.println(faculty);
    }

    /**
     * групує студентів по факультету
     * @param students списко студентів
     * @return Map<Faculty,List<Student>>
     */
    public static Map<Faculty,List<Student>> groupStudentsByFaculty(List<Student> students){
        return students.stream().collect(groupingBy(s->s.getGroup().getSpeciality().getFaculty()));
    }

    public static void printFile(String fileName){
        try (Stream<String> stream = Files.lines( Paths.get(fileName), StandardCharsets.UTF_8))
        {
            stream.forEach(out::println);
        }
		catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    public static void printFaculty(List<Faculty> faculties){
        out1251.println("***** Список факультетів ******");
        faculties.forEach(out::println);

    }
    public static void printSpeciality(List<Speciality> specialities){
        out1251.println("***** Список спеціальностей ******");
        specialities.forEach(out::println);
    }
    public static void printGroup(List<Group> groups){

        out1251.println("***** Список груп ******");
        groups.forEach(out::println);

    }
    public static void printStudent(List<Student> students){
        out1251.println("***** Список студентів ******");
        students.forEach(out::println);
    }

    public static int getInt(String msg) {
        while (true) {
            try {
                out1251.print(msg);
                String temp = scanner.nextLine();
                return Integer.parseInt(temp);
            } catch (Exception e) {

            }
        }

    }

    public static LocalDate getDate(String msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true) {
            try {
                out1251.print(msg);
                String temp = scanner.nextLine();
                LocalDate localDate = LocalDate.parse(temp, formatter);
                if (localDate != null) return localDate;
            } catch (Exception e) {

            }
        }

    }

    public static Faculty createFaculty() {
        Faculty faculty = null;
        while (faculty == null) {
            String code = getString("Введіть код факультету ");
            String name = getString("Введіть назву факультету ");
            String dean =getString("Введіть декана факультету ");
            try {
                faculty = new FacultyBuilder().withCode(code).withName(name).withDean(dean).build();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return faculty;


    }

    public static Speciality createSpeciality(Faculty faculty) {
        Speciality speciality = null;
        while (speciality == null) {

            String code = getString("Введіть код спеціальності ");
            String name = getString("Введіть назву спеціальності ");
            String info = getString("Введіть опис  спеціальності ");
            try {
                speciality = new SpecialityBuilder().withCode(code).withName(name).withInfo(info).withFaculty(faculty).build();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return speciality;


    }

    public static Group createGroup(Speciality speciality) {
        Group group = null;
        while (group == null) {
            String code = getString("Введіть код групи ");

            int course = getInt("Введіть курс ");
            try {
                group = new GroupBuilder().withCode(code).withCourse(course).withSpeciality(speciality).build();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return group;


    }

    public static Student createStudent(Group group) {
        Student student = null;
        while (student == null) {
            String code = getString("Введіть номер залікової книжки  ");
            String firstName = getString("Введіть ім`я  ");

            String lastName = getString("Введіть прізвище ");
            LocalDate birthDay = getDate("Введіть дату народження (dd.MM.yyyy) ");

            try {
                student = new StudentBuilder().withCreditBook(code).withFirstName(firstName)
                        .withLastName(lastName).withBirthDay(birthDay).withGroup(group).build();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return student;


    }


}
