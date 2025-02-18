package com;

import com.poc.model.PocModel;
import com.poc.presentation.PocPresenter;
import com.poc.presentation.PocView;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
		PocView pocView = new PocView();
		PocModel pocModel = new PocModel();

		PocPresenter pocPresenter = new PocPresenter(pocView, pocModel);

		latch.await();

	}
}
