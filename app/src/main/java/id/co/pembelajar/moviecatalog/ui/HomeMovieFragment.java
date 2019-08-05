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
import id.co.pembelajar.moviecatalog.adapter.MovieHomeAdapter;
import id.co.pembelajar.moviecatalog.model.MovieModels;
import id.co.pembelajar.moviecatalog.viewmodel.MovieViewModel;

public class HomeMovieFragment extends Fragment {
    private RecyclerView rvHomeMovie;
    private MovieHomeAdapter adapter;
    private ProgressBar loadingBar;
    private ShowingData showingData;


    public HomeMovieFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvHomeMovie = view.findViewById(R.id.recycler_movie_list);
        rvHomeMovie.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadingBar = view.findViewById(R.id.loadingBar);

        showingData = new ShowingData();

    }

    private void showLoading(boolean state){
        if (state){
            loadingBar.setIndeterminate(true);
            loadingBar.setVisibility(View.VISIBLE);
        }else {
            loadingBar.setIndeterminate(false);
            loadingBar.setVisibility(View.GONE);
        }
    }

    private class ShowingData extends AsyncTask<Void, Void, Void>{
        String language = getString(R.string.language_api);
        MovieViewModel viewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);

        private Observer<List<MovieModels.Results>> getToData = new Observer<List<MovieModels.Results>>(){
            @Override
            public void onChanged(List<MovieModels.Results> movieViewModels) {
                adapter = new MovieHomeAdapter(getActivity(), movieViewModels);
                rvHomeMovie.setAdapter(adapter);
                adapter.setOnItemClickCallback(new MovieHomeAdapter.OnItemClickCallback() {
                    @Override
                    public void onClickedItem(MovieModels.Results model) {
                        Toast.makeText(getActivity(), "Item Clicked "+ model.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        @Override
        protected void onPreExecute() {
            loadingBar.setIndeterminate(true);
            loadingBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            viewModel.sendReqData(language);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            viewModel.getSendReq().observe(getActivity(), getToData);
            loadingBar.setIndeterminate(false);
            loadingBar.setVisibility(View.GONE);
        }
    }
}
