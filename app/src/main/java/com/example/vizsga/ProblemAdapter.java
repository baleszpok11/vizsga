package com.example.vizsga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ProblemViewHolder> {

    private final List<Problem> problems;
    private final OnProblemClickListener onProblemClickListener;

    public ProblemAdapter(List<Problem> problems, OnProblemClickListener onProblemClickListener) {
        this.problems = problems;
        this.onProblemClickListener = onProblemClickListener;
    }

    @NonNull
    @Override
    public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.problem_item, parent, false);
        return new ProblemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemViewHolder holder, int position) {
        Problem problem = problems.get(position);
        holder.problemTextView.setText(problem.toString());
        holder.itemView.setOnClickListener(v -> onProblemClickListener.onProblemClick(problem));
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

    public interface OnProblemClickListener {
        void onProblemClick(Problem problem);
    }

    static class ProblemViewHolder extends RecyclerView.ViewHolder {
        TextView problemTextView;

        ProblemViewHolder(View itemView) {
            super(itemView);
            problemTextView = itemView.findViewById(R.id.problem_text);
        }
    }
}
