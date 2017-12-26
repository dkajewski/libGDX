package com.pupilla.dpk;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Screens.StartScreen;

public class Pupilla extends com.badlogic.gdx.Game {

	private StartScreen startScreen;
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		startScreen = new StartScreen(this);
		setScreen(startScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		startScreen.dispose();
	}
}