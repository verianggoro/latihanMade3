package id.co.pembelajar.moviecatalog.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.adapter.TvShowsAdapter;
import id.co.pembelajar.moviecatalog.model.TvShowsModels;
import id.co.pembelajar.moviecatalog.viewmodel.TvShowsViewModel;

public class HomeTvShowsFragment extends Fragment {
    private RecyclerView rvTvlist;
    private ProgressBar loadingBar;
    private TvShowsAdapter adapter;

    public HomeTvShowsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_tvshows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvlist = view.findViewById(R.id.recycler_tvshows_list);
        rvTvlist.setHasFixedSize(true);
        rvTvlist.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadingBar = view.findViewById(R.id.loadingBar);

        ShowingData showData = new ShowingData();
        showData.execute();

    }

    private class ShowingData extends AsyncTask<Void, Void, Void>{
        String language = getString(R.string.language_api);
        TvShowsViewModel viewModel = ViewModelProviders.of(getActivity()).get(TvShowsViewModel.class);
        private Observer<List<TvShowsModels.Result>> getToData = new Observer<List<TvShowsModels.Result>>() {
            @Override
            public void onChanged(List<TvShowsModels.Result> list) {
                adapter = new TvShowsAdapter(getActivity(), list);
                rvTvlist.setAdapter(adapter);
                adapter.setOnItemCallback(new TvShowsAdapter.OnItemCallback() {
                    @Override
                    public void onSetItemCallbak(TvShowsModels.Result tvModel) {
                        Toast.makeText(getContext(), "Item Clicked "+tvModel.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        @Override
        protected void onPreExecute() {
            loadingBar.setIndeterminate(true);
            loadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            viewModel.sendReqData(language);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            viewModel.getResultData().observe(getActivity(), getToData);
            loadingBar.setIndeterminate(false);
            loadingBar.setVisibility(View.GONE);
        }
    }
}
