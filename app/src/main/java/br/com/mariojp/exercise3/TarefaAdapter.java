package br.com.mariojp.exercise3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {

    public TarefaAdapter(Context context, int textViewResourceId, List<Tarefa> tarefas) {
        super(context, textViewResourceId, tarefas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Tarefa tarefa = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tarefa_item, parent, false);
        }

        TextView descricao = convertView.findViewById(R.id.descricao);
        TextView prioridade = convertView.findViewById(R.id.prioridade);

        descricao.setText(tarefa.getDescricao());
        prioridade.setText(String.valueOf(tarefa.getPrioridade()));

        return convertView;
    }
}
