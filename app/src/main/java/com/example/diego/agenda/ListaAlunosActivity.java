package com.example.diego.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.diego.agenda.dao.AlunoDAO;
import com.example.diego.agenda.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenteVaiParaOutraTela = new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                startActivity(intenteVaiParaOutraTela);
            }
        });
    }

    private void carregaLista()  {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos(); dao.close();

        ListView listaAlunos = (ListView)  findViewById(R.id.listaAlunos);
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,  android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
