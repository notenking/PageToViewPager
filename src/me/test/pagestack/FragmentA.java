package me.test.pagestack;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentA extends Fragment {

	private MyAdapter mPagerAdapter;
	private ViewPager vp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fa, container, false);
		vp = (ViewPager) view.findViewById(R.id.viewpage);
		
		new setAdapterTask().execute();
		return view;
	}
	
	private class setAdapterTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mPagerAdapter = new MyAdapter(FragmentA.this.getFragmentManager());
			vp.setAdapter(mPagerAdapter);
			vp.getAdapter().notifyDataSetChanged();
		}
	}

	public class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		public int getItemPosition(Object object) {
		    return POSITION_NONE;
		}
		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Fragment getItem(int position) {
			Log.e("MyAdapter","MyAdapter////getItem");
			return ArrayListFragment.newInstance(position);
		}

	}

	public static class ArrayListFragment extends ListFragment {
		int mNum;

		/**
		 * Create a new instance of CountingFragment, providing "num" as an
		 * argument.
		 */
		static ArrayListFragment newInstance(int num) {
			ArrayListFragment f = new ArrayListFragment();

			// Supply num input as an argument.
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);
			return f;
		}

		/**
		 * When creating, retrieve this instance's number from its arguments.
		 */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		}

		/**
		 * The Fragment's UI is just a simple text view showing its instance
		 * number.
		 */
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
			View tv = v.findViewById(R.id.text);
			((TextView) tv).setText("Fragment #" + mNum);
			Log.e("List onCreateView","View");
			return v;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			Log.e("List onActivityCreated","View");
			setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			Log.i("FragmentList", "Item clicked: " + id);

			// Create new fragment and transaction
			Fragment newFragment = new FragmentB();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(R.id.content, newFragment);
			transaction.addToBackStack(null);
			
			transaction.commit();
		}
	}
}
