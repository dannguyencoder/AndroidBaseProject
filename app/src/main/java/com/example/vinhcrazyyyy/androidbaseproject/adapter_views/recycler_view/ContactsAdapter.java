package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view.ContactsAdapter.ViewHolder;

import java.util.List;

/*Diffing larger changes
* Often times there are cases when changes to your list (i.e. sorting an existing list)
* and it cannot be easily determined whether each row changed. In this cases, you would
* normally have to call notifyDataSetChanged() on the entire adapter to update the entire
* screen, which eliminates the ability to perform animation sequences to showcases
* what changed*/

/*Using with ListAdapter
* The "ListAdapter" class, which is a new class introduced in the support library 21.1.0
* simplifies detecting whether an item was inserted, updated, or deleted*/

//First change your adapter to inherit from a RecyclerView.Adapter to ListAdapter
public class ContactsAdapter extends ListAdapter<Contact, ViewHolder> {

    List<Contact> mContacts;

    /*Note that a ListAdapter requires an extra generic parameter, which is the type of data
    * managed by this adapter. We also need to declare an item callback*/

    public static final DiffUtil.ItemCallback<Contact> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(Contact oldItem, Contact newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Contact oldItem, Contact newItem) {
            return (oldItem.getName() == newItem.getName() && oldItem.isOnline() == newItem.isOnline());
        }
    };

    protected ContactsAdapter(@NonNull ItemCallback<Contact> diffCallback) {
        /*Your adapter will also need to invoke this callback method*/
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    /*Instead of overriding the getItemCount(), remove it since the size
    * of the list will be managed by the ListAdapter class*/
    //remove this section
    @Override
    public int getItemCount() {
        return 0;
    }

    /*We also add a helper function to add more contacts. Anytime we wish to add more contacts,
    * will use this method. A submtList() provided by the ListAdapter will trigger
    * the comparison*/
    public void addMoreContacts(List<Contact> newContacts) {
        mContacts.addAll(newContacts);
        submitList(mContacts); //DiffUtil takes care of the check
    }

    /*Finally, we need to modify the onBindViewHolder to use the getItem() method instead*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Change from
        //remove this line
        Contact contact = mContacts.get(position);

        //to
        Contact contact = getItem(position);
    }

    /*After you create the DiffUtilCallback. Next, you would implement a swapItems()
    * method on your adapter to perform the diff and then invoke dispatchUpdates()
    * to notify the adapter whether the element was inserted, removed, moved, or changed*/
    public void swapItems(List<Contact> contacts) {
        //compute diffs
        ContactDiffCallback diffCallback = new ContactDiffCallback(this.mContacts, contacts);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        //clear contacts and add
        this.mContacts.clear();
        this.mContacts.addAll(contacts);

        diffResult.dispatchUpdatesTo(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
