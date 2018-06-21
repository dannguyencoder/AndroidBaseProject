package com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM;

import android.os.AsyncTask;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models.Organization;
import com.example.vinhcrazyyyy.androidbaseproject.persistence.roomORM.models.User;

public class RoomCRUDFragment extends BaseFragment {

    /*Basic CRUD operations

    There are a few key aspects of Room to note that differ slightly from
    traditional object relational mapping (ORM) frameworks:

    Usually, ORM frameworks expose a limited subset of queries.
    Tables are declared as Java objects, and the relations between these tables dictate
    the types of queries that can be performed. In Room, SQL inserts, updates, deletes, and
    complex joins are declared as Data Access Objects (DAO). The data returned from
    these queries simply are mapped to a Java object that will hold this information in memory.
     In this way, no assumptions are made about how the data can be accessed.
     The table definitions are separate from the actual queries performed.

    ORMs typically handle one-to-one and one-to-many relationships by determining the
    relationship between tables and expose an interface or a set of APIs that perform the
    SQL queries behind the scenes. Because of the performance implications,
    Room requires handling these relationships explicitly. The query needs to be defined
    as DAO objects, and the data returned will need to be mapped to Java objects.

    One additional change is that Room doesn't require defining your SQL tables as a
    single Java object. It allows you to maintain encapsulation between objects by
    allowing other Java objects to be included as part of a single table.
    Basic creation, read, update, and delete (CRUD) statements need to be defined in
    your data access object (DAO). One major difference is that queries cannot be done
    on the main thread. In addition, the objects can be inserted with an AsyncTask
    or using the runInTransaction() method:
*/
    @Override
    protected void setup(View view) {
        final Organization organization = new Organization();
        organization.setName("CodePath");

        final User user = new User();
        user.setName("John Doe");

        // Get the DAO
        final UserDao userDao = ((RestApplication) getApplicationContext()).getMyDatabase().userDao();

        // Define the task
        ((RestApplication) getApplicationContext()).getMyDatabase().runInTransaction(new Runnable() {
            @Override
            public void run() {
                userDao.insertOrganization(organization);
                userDao.insertModel(user);
            }
        });
    }

    private void queryRows() {
        final UserDao userDao = ((RestApplication) getApplicationContext()).getMyDatabase().userDao();

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                UserWithOrganization userWithOrganization = userDao.getWithOrgById(1);

                return null;
            };
        };

        // Make sure to run execute() to run this task!
        task.execute();
    }

    private void updateRows() {
        //Remember again that you need to dispatch the task in a separate thread:

        final UserDao userDao = ((RestApplication) getApplicationContext()).getMyDatabase().userDao();

        AsyncTask<User, Void, Void> task = new AsyncTask<User, Void, Void>() {

            @Override
            protected Void doInBackground(User... users) {
                userDao.insertUsers(users);
            }


        };
        task.execute(user);
    }

    /*Database Transactions*/
    private void databaseTransactions() {
        /*You can leverage the runInTransaction() method that comes with Room:*/
        ((RestApplication) getApplicationContext()).getMyDatabase().runInTransaction(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = ((RestApplication) getApplicationContext()).getMyDatabase().userDao();
                userDao.insertOrganization(organization);
                userDao.insertUser(user);
            }
        });
    }

    /*

    Question: Is it possible to do joins with Room?

    You must define the queries directly as Data Access Objects (DAO).
    The return type defined in these interfaces must match the data returned.
    See the example above in the querying rows section.

    There are a few key aspects of Room to note that differ slightly
    from traditional object relational mapping (ORM) frameworks:

    Usually, ORM frameworks expose a limited subset of queries.
    Tables are declared as Java objects, and the relations between these tables
    dictate the types of queries that can be performed. In Room, SQL inserts, updates,
    deletes, and complex joins are declared as Data Access Objects (DAO).
    The data returned from these queries simply are mapped to a Java object that
    will hold this information in memory. In this way, no assumptions are made about
    how the data can be accessed. The table definitions are separate from the actual
    queries performed.

    ORMs typically handle one-to-one and one-to-many relationships by determining
    the relationship between tables and expose an interface or a set of APIs that
    perform the SQL queries behind the scenes. Because of the performance implications,
     Room requires handling these relationships explicitly. The query needs to be defined
     as DAO objects, and the data returned will need to be mapped to Java objects.

    One additional change is that Room doesn't require defining your SQL tables as a
    single Java object. It allows you to maintain encapsulation between objects by
    allowing other Java objects to be included as part of a single table.
*/

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
