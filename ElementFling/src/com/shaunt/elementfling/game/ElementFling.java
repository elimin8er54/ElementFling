package com.shaunt.elementfling.game;

import com.shaunt.elementfling.framework.Screen;
import com.shaunt.elementfling.framework.impl.AndroidGame;
import com.shaunt.elementfling.game.screens.LoadingScreen;

public class ElementFling extends AndroidGame {
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}
