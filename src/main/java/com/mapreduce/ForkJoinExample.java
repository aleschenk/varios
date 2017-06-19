package com.mapreduce;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.util.Collections.unmodifiableList;

public class ForkJoinExample {

  /** VO to store the process results.
   *
   */
  public static class ProcessResult {

    private final int countOfProcessedRecords;

    private final List<String> errors;

    public ProcessResult(final int aCountOfProcessedRecords, final List<String> aListOfErrors) {
      // Validate invartiants
      // Precondtions.
      // Validation..
      countOfProcessedRecords = aCountOfProcessedRecords;
      errors = aListOfErrors;
    }

    public int countOfProcessedRecords() {
      return countOfProcessedRecords;
    }

    public List<String> errors() {
      return unmodifiableList(errors);
    }
  }

  public static class ProcessDocument extends RecursiveTask<ProcessResult> {

    private final List<String> documents;

    public ProcessDocument(final List<String> aListOfDocuments) {
      documents = aListOfDocuments;
    }

    @Override
    protected ProcessResult compute() {
      // documents
      return null;
    }

  }


  public static ForkJoinPool fjPool = new ForkJoinPool();

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2);


  }

}
