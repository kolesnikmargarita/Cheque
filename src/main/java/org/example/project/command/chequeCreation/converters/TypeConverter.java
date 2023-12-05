package org.example.project.command.chequeCreation.converters;

public interface TypeConverter<TInput, TOutput> {
    public TOutput translate(TInput inputInformation);
}
