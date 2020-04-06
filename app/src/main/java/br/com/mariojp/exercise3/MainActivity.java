package br.com.mariojp.exercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaTarefasView;
    private EditText description;
    private EditText priority;
    private Button addTask;
    private Button removeTask;
    private List<Tarefa> listaTarefas;
    private TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTarefasView = findViewById(R.id.listView);
        description = findViewById(R.id.editDescricao);
        priority = findViewById(R.id.editPrioridade);
        addTask = findViewById(R.id.buttonAdicionar);
        removeTask = findViewById(R.id.buttonRemover);

        listaTarefas = new ArrayList<Tarefa>();
        tarefaAdapter = new TarefaAdapter(getBaseContext(), R.layout.tarefa_item, listaTarefas);
        listaTarefasView.setAdapter(tarefaAdapter);

        removeTask.setEnabled(false);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(description.getText().toString().trim().equals("") || priority.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Todos os campos devem ser preenchidos!",
                            Toast.LENGTH_LONG).show();
                }else {
                    String des = description.getText().toString();
                    Integer pri = Integer.parseInt(priority.getText().toString());

                    Tarefa tarefa = new Tarefa(des, pri);
                    if(listaTarefas.contains(tarefa)){
                        if(tarefa.equals(tarefa)){
                            Toast.makeText(MainActivity.this, "Tarefa já cadastrada.", Toast.LENGTH_LONG).show();
                        }else {
                            if(tarefa.verificarPrioridade(tarefa)){
                                Toast.makeText(MainActivity.this, "A prioridade deve estar entre 1 e 10.", Toast.LENGTH_LONG).show();
                            }else {
                                tarefaAdapter.add(tarefa);
                            }
                        }
                    }else{
                        if(tarefa.verificarPrioridade(tarefa)){
                            Toast.makeText(MainActivity.this, "A prioridade deve estar entre 1 e 10.", Toast.LENGTH_LONG).show();
                        }else {
                            tarefaAdapter.add(tarefa);
                        }
                    }

                    Collections.sort(listaTarefas);
                    removeTask.setEnabled(true);
                }
            }
        });

        removeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaTarefas.size() > 0){
                    listaTarefas.remove(0);
                    tarefaAdapter.notifyDataSetChanged();
                    if(listaTarefas.size() <= 0) {
                        removeTask.setEnabled(false);
                    }
                }
            }
        });

        listaTarefasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listaTarefas.size() > 0){
                    listaTarefas.remove(position);
                    tarefaAdapter.notifyDataSetChanged();
                    if(listaTarefas.size() <= 0) {
                        removeTask.setEnabled(false);
                    }
                }
            }
        });


    }
}
