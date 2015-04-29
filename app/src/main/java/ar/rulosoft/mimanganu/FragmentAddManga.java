package ar.rulosoft.mimanganu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.rulosoft.mimanganu.adapters.ServerRecAdapter;
import ar.rulosoft.mimanganu.adapters.ServerRecAdapter.OnServerClickListener;
import ar.rulosoft.mimanganu.servers.EsMangaHere;
import ar.rulosoft.mimanganu.servers.EsNineMangaCom;
import ar.rulosoft.mimanganu.servers.HeavenMangaCom;
import ar.rulosoft.mimanganu.servers.ItNineMangaCom;
import ar.rulosoft.mimanganu.servers.KissManga;
import ar.rulosoft.mimanganu.servers.LectureEnLigne;
import ar.rulosoft.mimanganu.servers.MangaFox;
import ar.rulosoft.mimanganu.servers.MangaHere;
import ar.rulosoft.mimanganu.servers.MangaPanda;
import ar.rulosoft.mimanganu.servers.ServerBase;
import ar.rulosoft.mimanganu.servers.StarkanaCom;
import ar.rulosoft.mimanganu.servers.SubManga;
import ar.rulosoft.mimanganu.servers.TusMangasOnlineCom;

public class FragmentAddManga extends Fragment implements OnServerClickListener {

    RecyclerView lista_server;
    ServerRecAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rView = inflater.inflate(R.layout.fragment_add_manga, container, false);
        lista_server = (RecyclerView) rView.findViewById(R.id.lista_de_servers);
        lista_server.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        adapter = new ServerRecAdapter(new ServerBase[]{new HeavenMangaCom(),
                new SubManga(),// new EsMangaCom(),
                new EsNineMangaCom(), new EsMangaHere(), new TusMangasOnlineCom(), new MangaPanda(), new MangaHere(), new MangaFox(), new StarkanaCom(),
                new KissManga(), new LectureEnLigne(), new ItNineMangaCom()});
        lista_server.setAdapter(adapter);
        adapter.setOnServerClickListener(FragmentAddManga.this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onServerClick(ServerBase server) {
        Intent intent;
        if (server.tieneNavegacionVisual())
            intent = new Intent(getActivity(), ActivityServerVisualNavegacion.class);// ActivityServerListadeMangas
        else
            intent = new Intent(getActivity(), ActivityServerListadeMangas.class);
        intent.putExtra(ActivityMisMangas.SERVER_ID, server.getServerID());
        getActivity().startActivity(intent);
    }

}