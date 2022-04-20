package com.example.java_unittest_mitch.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Inject;

import dagger.MapKey;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@MapKey
public @interface ViewModelKey
{
    Class<? extends ViewModel> value();



}
