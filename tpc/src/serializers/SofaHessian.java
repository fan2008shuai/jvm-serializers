package serializers;

import com.caucho.hessian.io.*;
import data.media.MediaContent;

import java.io.*;
import java.lang.reflect.Array;

public class SofaHessian
{
    public static void register(TestGroups groups)
    {
        groups.media.add(JavaBuiltIn.mediaTransformer, new HessianSerializer<MediaContent>(MediaContent.class), 
                new SerFeatures(
                        SerFormat.BIN_CROSSLANG,
                        SerGraph.FULL_GRAPH,
                        SerClass.ZERO_KNOWLEDGE,""
                ) 
        );
    }

    // ------------------------------------------------------------
    // Serializer (just one)

	public final static class HessianSerializer<T> extends Serializer<T>
	{
	    private final Class<T> clz;

        private SerializerFactory serializerFactory = new SerializerFactory();
	    
	    public HessianSerializer(Class<T> c) { clz = c; }
	    
            public String getName() { return "sofa-hessian"; }

            @SuppressWarnings("unchecked")
            public T deserialize(byte[] array) throws Exception
	    {
//	        ByteArrayInputStream in = new ByteArrayInputStream(array);
//	        Hessian2StreamingInput hin = new Hessian2StreamingInput(in);
//            Hessian2Input hin = new Hessian2Input(new ByteArrayInputStream(array));
//            hin.setSerializerFactory(serializerFactory);
//
//	        return (T) hin.readObject();

            ByteArrayInputStream in = new ByteArrayInputStream(array);
            Hessian2StreamingInput hin = new Hessian2StreamingInput(in);
            hin.setSerializerFactory(serializerFactory);
            return (T) hin.readObject();

//            ByteArrayInputStream in = new ByteArrayInputStream(array);
//            Hessian2Input hin = new Hessian2Input(in);
//            hin.setSerializerFactory(serializerFactory);
//            return (T) hin.readObject();

	    }

	    public byte[] serialize(T data) throws IOException
	    {
//	        ByteArrayOutputStream out = outputStream(data);
//	        Hessian2StreamingOutput hout = new Hessian2StreamingOutput(out);
//            return out.toByteArray();
//            ByteArrayOutputStream byteArray = outputStream(data);
//            Hessian2Output hout = new Hessian2Output(byteArray);
//            hout.setSerializerFactory(serializerFactory);
//
//            hout.writeObject(data);
//
//            return byteArray.toByteArray();

            // this is ok
            ByteArrayOutputStream out = outputStream(data);
            Hessian2StreamingOutput hout = new Hessian2StreamingOutput(out);
            hout.getHessian2Output().setSerializerFactory(serializerFactory);
            hout.writeObject(data);
            return out.toByteArray();

//            ByteArrayOutputStream out = outputStream(data);
//            Hessian2Output hout = new Hessian2Output(out);
//            hout.setSerializerFactory(serializerFactory);
//            hout.writeObject(data);
//            return out.toByteArray();
	    }

//            @Override
//            public final void serializeItems(T[] items, OutputStream out) throws Exception
//            {
////                Hessian2StreamingOutput hout = new Hessian2StreamingOutput(out);
//                Hessian2Output hout = new Hessian2Output(out);
//                hout.setSerializerFactory(serializerFactory);
//                for (Object item : items) {
//                    hout.writeObject(item);
//                }
//                hout.flush();
//                hout.close();
//            }
//
//            @SuppressWarnings("unchecked")
//            @Override
//            public T[] deserializeItems(InputStream in, int numberOfItems) throws Exception
//            {
////                Hessian2StreamingInput hin = new Hessian2StreamingInput(in);
//                Hessian2Input hin = new Hessian2Input(in);
//                hin.setSerializerFactory(serializerFactory);
//
//                T[] result = (T[]) Array.newInstance(clz, numberOfItems);
//                for (int i = 0; i < numberOfItems; ++i) {
//                    result[i] = (T) hin.readObject();
//                }
//                hin.close();
//                return result;
//            }
	}
}
