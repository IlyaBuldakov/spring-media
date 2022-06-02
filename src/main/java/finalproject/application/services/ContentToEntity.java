package finalproject.application.services;

import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.file.File;
import io.vavr.control.Either;

import java.util.concurrent.Future;
import java.util.concurrent.Future;

public interface ContentToEntity {
  Future<Either<Failure, Content>> convert(byte[] file);
}



