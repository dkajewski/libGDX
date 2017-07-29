package com.pupilla.dpk;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pupilla.dpk.Screens.PlayScreen;

public class Pupilla extends com.badlogic.gdx.Game {


	public static final PlayScreen mainScreen = new PlayScreen();

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		setScreen(mainScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		mainScreen.dispose();
	}
}