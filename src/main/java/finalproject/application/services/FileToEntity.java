package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.file.File;
import io.vavr.control.Either;

import java.util.concurrent.Future;

public interface FileToEntity {
  Future<Either<Failure, File>> convert(byte[] file);
}
