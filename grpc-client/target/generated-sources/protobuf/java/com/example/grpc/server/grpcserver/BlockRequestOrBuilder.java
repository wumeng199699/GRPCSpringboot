// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BlockService.proto

package com.example.grpc.server.grpcserver;

public interface BlockRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.example.grpc.server.grpcserver.BlockRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
   */
  boolean hasA();
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
   */
  com.example.grpc.server.grpcserver.Matrix getA();
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
   */
  com.example.grpc.server.grpcserver.MatrixOrBuilder getAOrBuilder();

  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
   */
  boolean hasB();
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
   */
  com.example.grpc.server.grpcserver.Matrix getB();
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
   */
  com.example.grpc.server.grpcserver.MatrixOrBuilder getBOrBuilder();
}
