// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BlockService.proto

package com.example.grpc.server.grpcserver;

/**
 * Protobuf type {@code com.example.grpc.server.grpcserver.BlockRequest}
 */
public  final class BlockRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.example.grpc.server.grpcserver.BlockRequest)
    BlockRequestOrBuilder {
  // Use BlockRequest.newBuilder() to construct.
  private BlockRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BlockRequest() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private BlockRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            com.example.grpc.server.grpcserver.Matrix.Builder subBuilder = null;
            if (a_ != null) {
              subBuilder = a_.toBuilder();
            }
            a_ = input.readMessage(com.example.grpc.server.grpcserver.Matrix.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(a_);
              a_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            com.example.grpc.server.grpcserver.Matrix.Builder subBuilder = null;
            if (b_ != null) {
              subBuilder = b_.toBuilder();
            }
            b_ = input.readMessage(com.example.grpc.server.grpcserver.Matrix.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(b_);
              b_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.grpc.server.grpcserver.BlockServiceOuterClass.internal_static_com_example_grpc_server_grpcserver_BlockRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.grpc.server.grpcserver.BlockServiceOuterClass.internal_static_com_example_grpc_server_grpcserver_BlockRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.grpc.server.grpcserver.BlockRequest.class, com.example.grpc.server.grpcserver.BlockRequest.Builder.class);
  }

  public static final int A_FIELD_NUMBER = 1;
  private com.example.grpc.server.grpcserver.Matrix a_;
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
   */
  public boolean hasA() {
    return a_ != null;
  }
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
   */
  public com.example.grpc.server.grpcserver.Matrix getA() {
    return a_ == null ? com.example.grpc.server.grpcserver.Matrix.getDefaultInstance() : a_;
  }
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
   */
  public com.example.grpc.server.grpcserver.MatrixOrBuilder getAOrBuilder() {
    return getA();
  }

  public static final int B_FIELD_NUMBER = 2;
  private com.example.grpc.server.grpcserver.Matrix b_;
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
   */
  public boolean hasB() {
    return b_ != null;
  }
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
   */
  public com.example.grpc.server.grpcserver.Matrix getB() {
    return b_ == null ? com.example.grpc.server.grpcserver.Matrix.getDefaultInstance() : b_;
  }
  /**
   * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
   */
  public com.example.grpc.server.grpcserver.MatrixOrBuilder getBOrBuilder() {
    return getB();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (a_ != null) {
      output.writeMessage(1, getA());
    }
    if (b_ != null) {
      output.writeMessage(2, getB());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (a_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getA());
    }
    if (b_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getB());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.grpc.server.grpcserver.BlockRequest)) {
      return super.equals(obj);
    }
    com.example.grpc.server.grpcserver.BlockRequest other = (com.example.grpc.server.grpcserver.BlockRequest) obj;

    boolean result = true;
    result = result && (hasA() == other.hasA());
    if (hasA()) {
      result = result && getA()
          .equals(other.getA());
    }
    result = result && (hasB() == other.hasB());
    if (hasB()) {
      result = result && getB()
          .equals(other.getB());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasA()) {
      hash = (37 * hash) + A_FIELD_NUMBER;
      hash = (53 * hash) + getA().hashCode();
    }
    if (hasB()) {
      hash = (37 * hash) + B_FIELD_NUMBER;
      hash = (53 * hash) + getB().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.grpc.server.grpcserver.BlockRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.grpc.server.grpcserver.BlockRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.example.grpc.server.grpcserver.BlockRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.example.grpc.server.grpcserver.BlockRequest)
      com.example.grpc.server.grpcserver.BlockRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.grpc.server.grpcserver.BlockServiceOuterClass.internal_static_com_example_grpc_server_grpcserver_BlockRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.grpc.server.grpcserver.BlockServiceOuterClass.internal_static_com_example_grpc_server_grpcserver_BlockRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.grpc.server.grpcserver.BlockRequest.class, com.example.grpc.server.grpcserver.BlockRequest.Builder.class);
    }

    // Construct using com.example.grpc.server.grpcserver.BlockRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      if (aBuilder_ == null) {
        a_ = null;
      } else {
        a_ = null;
        aBuilder_ = null;
      }
      if (bBuilder_ == null) {
        b_ = null;
      } else {
        b_ = null;
        bBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.grpc.server.grpcserver.BlockServiceOuterClass.internal_static_com_example_grpc_server_grpcserver_BlockRequest_descriptor;
    }

    public com.example.grpc.server.grpcserver.BlockRequest getDefaultInstanceForType() {
      return com.example.grpc.server.grpcserver.BlockRequest.getDefaultInstance();
    }

    public com.example.grpc.server.grpcserver.BlockRequest build() {
      com.example.grpc.server.grpcserver.BlockRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.example.grpc.server.grpcserver.BlockRequest buildPartial() {
      com.example.grpc.server.grpcserver.BlockRequest result = new com.example.grpc.server.grpcserver.BlockRequest(this);
      if (aBuilder_ == null) {
        result.a_ = a_;
      } else {
        result.a_ = aBuilder_.build();
      }
      if (bBuilder_ == null) {
        result.b_ = b_;
      } else {
        result.b_ = bBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.grpc.server.grpcserver.BlockRequest) {
        return mergeFrom((com.example.grpc.server.grpcserver.BlockRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.grpc.server.grpcserver.BlockRequest other) {
      if (other == com.example.grpc.server.grpcserver.BlockRequest.getDefaultInstance()) return this;
      if (other.hasA()) {
        mergeA(other.getA());
      }
      if (other.hasB()) {
        mergeB(other.getB());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.example.grpc.server.grpcserver.BlockRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.grpc.server.grpcserver.BlockRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.example.grpc.server.grpcserver.Matrix a_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.grpc.server.grpcserver.Matrix, com.example.grpc.server.grpcserver.Matrix.Builder, com.example.grpc.server.grpcserver.MatrixOrBuilder> aBuilder_;
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public boolean hasA() {
      return aBuilder_ != null || a_ != null;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public com.example.grpc.server.grpcserver.Matrix getA() {
      if (aBuilder_ == null) {
        return a_ == null ? com.example.grpc.server.grpcserver.Matrix.getDefaultInstance() : a_;
      } else {
        return aBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public Builder setA(com.example.grpc.server.grpcserver.Matrix value) {
      if (aBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        a_ = value;
        onChanged();
      } else {
        aBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public Builder setA(
        com.example.grpc.server.grpcserver.Matrix.Builder builderForValue) {
      if (aBuilder_ == null) {
        a_ = builderForValue.build();
        onChanged();
      } else {
        aBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public Builder mergeA(com.example.grpc.server.grpcserver.Matrix value) {
      if (aBuilder_ == null) {
        if (a_ != null) {
          a_ =
            com.example.grpc.server.grpcserver.Matrix.newBuilder(a_).mergeFrom(value).buildPartial();
        } else {
          a_ = value;
        }
        onChanged();
      } else {
        aBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public Builder clearA() {
      if (aBuilder_ == null) {
        a_ = null;
        onChanged();
      } else {
        a_ = null;
        aBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public com.example.grpc.server.grpcserver.Matrix.Builder getABuilder() {
      
      onChanged();
      return getAFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    public com.example.grpc.server.grpcserver.MatrixOrBuilder getAOrBuilder() {
      if (aBuilder_ != null) {
        return aBuilder_.getMessageOrBuilder();
      } else {
        return a_ == null ?
            com.example.grpc.server.grpcserver.Matrix.getDefaultInstance() : a_;
      }
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix a = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.grpc.server.grpcserver.Matrix, com.example.grpc.server.grpcserver.Matrix.Builder, com.example.grpc.server.grpcserver.MatrixOrBuilder> 
        getAFieldBuilder() {
      if (aBuilder_ == null) {
        aBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.example.grpc.server.grpcserver.Matrix, com.example.grpc.server.grpcserver.Matrix.Builder, com.example.grpc.server.grpcserver.MatrixOrBuilder>(
                getA(),
                getParentForChildren(),
                isClean());
        a_ = null;
      }
      return aBuilder_;
    }

    private com.example.grpc.server.grpcserver.Matrix b_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.grpc.server.grpcserver.Matrix, com.example.grpc.server.grpcserver.Matrix.Builder, com.example.grpc.server.grpcserver.MatrixOrBuilder> bBuilder_;
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public boolean hasB() {
      return bBuilder_ != null || b_ != null;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public com.example.grpc.server.grpcserver.Matrix getB() {
      if (bBuilder_ == null) {
        return b_ == null ? com.example.grpc.server.grpcserver.Matrix.getDefaultInstance() : b_;
      } else {
        return bBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public Builder setB(com.example.grpc.server.grpcserver.Matrix value) {
      if (bBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        b_ = value;
        onChanged();
      } else {
        bBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public Builder setB(
        com.example.grpc.server.grpcserver.Matrix.Builder builderForValue) {
      if (bBuilder_ == null) {
        b_ = builderForValue.build();
        onChanged();
      } else {
        bBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public Builder mergeB(com.example.grpc.server.grpcserver.Matrix value) {
      if (bBuilder_ == null) {
        if (b_ != null) {
          b_ =
            com.example.grpc.server.grpcserver.Matrix.newBuilder(b_).mergeFrom(value).buildPartial();
        } else {
          b_ = value;
        }
        onChanged();
      } else {
        bBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public Builder clearB() {
      if (bBuilder_ == null) {
        b_ = null;
        onChanged();
      } else {
        b_ = null;
        bBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public com.example.grpc.server.grpcserver.Matrix.Builder getBBuilder() {
      
      onChanged();
      return getBFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    public com.example.grpc.server.grpcserver.MatrixOrBuilder getBOrBuilder() {
      if (bBuilder_ != null) {
        return bBuilder_.getMessageOrBuilder();
      } else {
        return b_ == null ?
            com.example.grpc.server.grpcserver.Matrix.getDefaultInstance() : b_;
      }
    }
    /**
     * <code>.com.example.grpc.server.grpcserver.Matrix b = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.grpc.server.grpcserver.Matrix, com.example.grpc.server.grpcserver.Matrix.Builder, com.example.grpc.server.grpcserver.MatrixOrBuilder> 
        getBFieldBuilder() {
      if (bBuilder_ == null) {
        bBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.example.grpc.server.grpcserver.Matrix, com.example.grpc.server.grpcserver.Matrix.Builder, com.example.grpc.server.grpcserver.MatrixOrBuilder>(
                getB(),
                getParentForChildren(),
                isClean());
        b_ = null;
      }
      return bBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.example.grpc.server.grpcserver.BlockRequest)
  }

  // @@protoc_insertion_point(class_scope:com.example.grpc.server.grpcserver.BlockRequest)
  private static final com.example.grpc.server.grpcserver.BlockRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.grpc.server.grpcserver.BlockRequest();
  }

  public static com.example.grpc.server.grpcserver.BlockRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BlockRequest>
      PARSER = new com.google.protobuf.AbstractParser<BlockRequest>() {
    public BlockRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BlockRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BlockRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BlockRequest> getParserForType() {
    return PARSER;
  }

  public com.example.grpc.server.grpcserver.BlockRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

