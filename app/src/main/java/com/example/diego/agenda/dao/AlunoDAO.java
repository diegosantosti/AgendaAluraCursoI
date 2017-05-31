package com.example.diego.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.diego.agenda.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by projeto on 31/05/2017.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    static int VERSION_BD = 1;

    public AlunoDAO(Context context ) {
        super(context, "Agenda", null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE Alunos " +
                        "(id INTEGER PRIMARY KEY, " +
                        "nome TEXT NOT NULL, " +
                        "endereço TEXT, " +
                        "telefone TEXT, " +
                        "site TEXT, " +
                        "nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insereAluno(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome",aluno.getNome());
        dados.put("endereço",aluno.getEndereco());
        dados.put("telefone",aluno.getTelefone());
        dados.put("site",aluno.getSite());
        dados.put("nota",aluno.getNota());

        db.insert("Alunos",null,dados);

    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        while (c.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereço")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }
}
