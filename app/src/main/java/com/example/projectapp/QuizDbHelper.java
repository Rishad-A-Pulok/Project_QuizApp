package com.example.projectapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SmartQuizApp.db";
    private static final int DATABASE_VERSION = 4;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Geography");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Android is developed by:",
                "Android.Inc", "Apple", "Microsoft", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q1);
        Question q2 = new Question("Android is based on which Kernal?",
                "Mac", "Linux", "Windows", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q2);
        Question q3 = new Question("What is APK in android?",
                "Android Packages", "Android Pack", "Android Package Kit", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q3);
        Question q4 = new Question("What is the latest version of android?",
                "Android 11", "Android 9", "Android 8", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q4);
        Question q5 = new Question("What does API stand for?",
                "Accelerated Programming Interface", "Application Programming Interface", "Algorithmic Protocol Interface", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q5);
        Question q6 = new Question("Which media format does not supported by android?",
                "MIDI", "AVI", "MP4", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q6);
        Question q7 = new Question("In which directory XML layout files are stored?",
                "/res/values", "/src", "/res/layout", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q7);
        Question q8 = new Question("Which code used by android is not an open source?",
                "Device Driver", "WiFi Driver", "Video Driver", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q8);
        Question q9 = new Question("What is JSON in android?",
                "Java Script Oriented Notation", "Java Script Object Native", "Java Script Object Notation", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q9);
        Question q10 = new Question("How to find the JSON element length in android JSON?",
                "count()", "length()", "add()", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q10);
        Question q11 = new Question("What is JNI in andoroid?",
                "Java Interface", "Java Native Interface", "Java Network Interface", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q11);
        Question q12 = new Question("How to upgrade SQlite the database from a lower version to higher version in android SQlite?",
                "Using intent", "Using cursor", "Using helper Class", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q12);
        Question q13 = new Question("Action Bar can be associated to",
                "Both Activity and Fragment", "Only Activities", "Only Fragments", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q13);
        Question q14 = new Question("Which component is not activated by an Intent?",
                "contentProvider", "services", "activity", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q14);
        Question q15 = new Question("Once installed on a device, each Android application lives in_______",
                "device memory", "external memory", "security sandbox", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q15);
        Question q16 = new Question("Which planet is known as watery planet?",
                "Jupiter", "Mars", "Earth", 3,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q16);
        Question q17 = new Question("A blackhole is a _______",
                "Contracted star with intense gravitational pull", "Pulsating star", "Star with no atmosphere", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q17);
        Question q18 = new Question("Milky Way galaxy was first seen by",
                "Newton", "Martin Schmidt", "Galileo", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q18);
        Question q19 = new Question("Halley's comet appears once in a period of_____",
                "27 years", "50 years", "76 years", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        insertQuestion(q19);
        Question q20 = new Question("Which planet is known as blue planet?",
                "Uranus", "Mars", "Earth", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q20);
        Question q21 = new Question("2 + 2 = ",
                "10", "22", "4", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q21);
        Question q22 = new Question("Average of first 50 numbers _____",
                "25.0", "22.5", "24.8", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q22);
        Question q23 = new Question("1004 / 2 = ",
                "520", "5002", "502", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q23);
        Question q24 = new Question("A clock strikes once at 1 o’clock, twice at 2 o’clock, thrice at 3 o’clock and so on. How many times will it strike in 24 hours?",
                "156", "78", "144", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q24);
        Question q25 = new Question("Which of the following numbers gives 240 when added to its own square?",
                "10", "22", "15", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q25);
        Question q26 = new Question("The wages of 10 workers for a six-day week is $ 1200. What are the one day’s wages of 4 workers?",
                "$100", "$220", "$80", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q26);

    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}