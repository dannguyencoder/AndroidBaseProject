package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view;

import android.support.v7.util.DiffUtil;

import java.util.List;

/*Using with DiffUtil
* The ListAdapter is built on top the DiffUtil class but requires less boilerplate code.
* You can see below what the steps are needed in order to accomplish the same goal.
* You do not need to follow the steps below if you are already using ListAdapter.
*
* The DiffUtil class, which was added in the v24.2.0 of the support library, helps compute
* the difference between the old and the new list. This class uses the same algorithm used
* for computing line changes in source code (the "diff utility" program)
* https://en.wikipedia.org/wiki/Diff_utility
* so it usually fairly fast. It is recommended however for larger lists that you execute
* this computation in a background thread.
*
* To use the DiffUtil class, you need to first implement a class
* that implements DiffUtil.Callback that accepts the old and new list*/
public class ContactDiffCallback extends DiffUtil.Callback {

    List<Contact> mOldList;
    List<Contact> mNewList;

    public ContactDiffCallback(List<Contact> mOldList, List<Contact> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        //add a unique ID property on Contact and expost getId() method
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Contact oldContact = mOldList.get(oldItemPosition);
        Contact newContact = mNewList.get(newItemPosition);

        if (oldContact.getName() == newContact.getName()
                && oldContact.isOnline() == newContact.isOnline()) {
            return true;
        }
        return false;
    }
}
