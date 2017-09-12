package com.android.cuentacuentosinteractivo.customs;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;

import com.android.cuentacuentosinteractivo.utils.Constants;


public class CustomActionDrawable implements Runnable {


	private int duration = 0;
	/** Con este flag evitamos que se ejecute por segunda vez la animación de retorno */
	private boolean finish = false;
	/** Este flag nos indica que si es un animación falsa para rellenar un silencio */
	private boolean mFake = false;
	/** Este flag nos indica si esta animación es simultánea a la siguiente */
	private boolean mSimul = false;
	/** Handles the animation callback. */
	private Handler mAnimationHandler;
	/** Frame Animation */
	private AnimationDrawable mAnim;
	/** Transition Animation */
	private TransitionDrawable mTrans;
	/** Drawable. Transition or Animation */
	private int mType ;
    /** Index of the drawable */
    private int mIndex ;

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isSimul() {
		return mSimul;
	}

	public boolean isFinished() {
		
		return finish;
	}

	public void reset() {
		
		finish = false;
	}

	public CustomActionDrawable(TransitionDrawable transition, int duration, int type, int index){

		mTrans = transition;
		this.setDuration(duration);
		mType = type;
		if (mType == Constants.Actions.SIMUL || mType == Constants.Actions.STATIC)
			mSimul = true;
        mIndex = index;
	}

	@Override
	public void run() {

		switch (mType) {

			case Constants.Actions.ANIM: {

				mAnim = (AnimationDrawable) mTrans.getDrawable(mIndex);
				mAnim.start();
				/*
				 * Call super.start() to call the base class start animation method.
				 * Then add a handler to call onAnimationFinish() when the total
				 * duration for the animation has passed
				 */
				mAnimationHandler = new Handler();
				mAnimationHandler.postDelayed(new Runnable() {

					public void run() {

						mAnim.stop();
					}
				}, CustomActionDrawable.this.getDuration());

				break;
			}
			case Constants.Actions.SILENT: {

				break;
			}
			case Constants.Actions.SIMUL: {

				mAnim = (AnimationDrawable) mTrans.getDrawable(mIndex);
				mAnim.start();
				/*
				 * Call super.start() to call the base class start animation method.
				 * Then add a handler to call onAnimationFinish() when the total
				 * duration for the animation has passed
				 */
				mAnimationHandler = new Handler();
				mAnimationHandler.postDelayed(new Runnable() {

					public void run() {

						mAnim.stop();
					}
				}, CustomActionDrawable.this.getDuration());

				break;
			}
			case Constants.Actions.STATIC: {

				if (mIndex == 0) {

					mTrans.startTransition(duration);
				}
				else {

					mTrans.reverseTransition(duration);
				}

				break;
			}

		}


    }
}
