package com.example.cmathew.forestmoon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GraphConnectionAdapter extends RecyclerView.Adapter<GraphConnectionAdapter.ViewHolder> {
    private boolean[][] connections;

    public GraphConnectionAdapter(boolean[][] connections) {
        this.connections = connections;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_node_connection, parent, false);

        return new GraphConnectionAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int row = position / connections.length;
        int col = position % connections.length;
        boolean isConnected = connections[row][col];

        holder.connectionText.setText(String.valueOf(isConnected ? 1 : 0));
    }

    @Override
    public int getItemCount() {
        return (int) Math.pow(connections.length, 2);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView connectionText;

        public ViewHolder(View view) {
            super(view);

            this.connectionText = view.findViewById(R.id.node_connection_text);
        }
    }
}
