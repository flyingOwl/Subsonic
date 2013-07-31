/*
 This file is part of Subsonic.

 Subsonic is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Subsonic is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Subsonic.  If not, see <http://www.gnu.org/licenses/>.

 Copyright 2009 (C) Sindre Mehus
 */
package github.daneren2005.dsub.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import github.daneren2005.dsub.R;
import github.daneren2005.dsub.domain.PodcastChannel;
import github.daneren2005.dsub.util.FileUtil;
import java.io.File;

public class PodcastChannelView extends UpdateView {
	private static final String TAG = PodcastChannelView.class.getSimpleName();

	private Context context;
	private PodcastChannel channel;
	
	private TextView titleView;

	public PodcastChannelView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.artist_list_item, this, true);

		titleView = (TextView) findViewById(R.id.artist_name);
		ImageButton starButton = (ImageButton) findViewById(R.id.artist_star);
		moreButton = (ImageView) findViewById(R.id.artist_more);
		moreButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				v.showContextMenu();
			}
		});
	}

	public void setPodcastChannel(Object obj) {
		channel = (PodcastChannel) obj;
		if(channel.getName() != null) {
			titleView.setText(channel.getName());
		} else {
			titleView.setText(channel.getUrl());
		}
		file = FileUtil.getPodcastDirectory(context, channel);
	}
	
	@Override
	protected void updateBackground() {
		exists = file.exists();
	}
}
