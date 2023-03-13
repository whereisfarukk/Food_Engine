package com.example.food_engine.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_engine.DBHelper;
import com.example.food_engine.DetailActivity;
import com.example.food_engine.Models.OrdersModel;
import com.example.food_engine.OrderActivity;
import com.example.food_engine.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewholder> {

    ArrayList<OrdersModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderQuantity.setText(model.getOrderQuantity());
        holder.price.setText(model.getPrice());

        holder.itemView.setOnClickListener(view -> {
             Intent intent = new Intent(context, DetailActivity.class);
             intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));
             intent.putExtra("type", 2);
             context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(view -> {
            DBHelper helper = new DBHelper(context);
            new AlertDialog.Builder(context)
                    .setTitle("Cancel")
                    .setIcon(R.drawable.warning)
                    .setMessage("Are you sure you want to cancel the order?")
                    .setPositiveButton("yes", (dialogInterface, i) -> {
                        if (helper.deleteOrder(model.getOrderNumber()) > 0) {
                            Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, OrderActivity.class));
                        } else {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel()).show();
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView soldItemName, orderQuantity, price;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderImage);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            orderQuantity = itemView.findViewById(R.id.orderQuantity);
            price = itemView.findViewById(R.id.soldItemPrice);
        }
    }
}
