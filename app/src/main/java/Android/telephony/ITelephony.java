package Android.telephony;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITelephony extends IInterface {
    boolean endCall() throws RemoteException;

    void answerRingingCall() throws RemoteException;

    boolean enableDataConnectivity() throws RemoteException;

    boolean disableDataConnectivity() throws RemoteException;

    boolean isDataConnectivityPossible() throws RemoteException;

    public abstract static class Stub extends Binder implements ITelephony {
        private static final String DESCRIPTOR = "com.android.internal.telephony.ITelephony";
        static final int TRANSACTION_endCall = 1;
        static final int TRANSACTION_answerRingingCall = 2;
        static final int TRANSACTION_enableDataConnectivity = 3;
        static final int TRANSACTION_disableDataConnectivity = 4;
        static final int TRANSACTION_isDataConnectivityPossible = 5;

        public Stub() {
            this.attachInterface(this, "com.android.internal.telephony.ITelephony");
        }

        public static ITelephony asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.android.internal.telephony.ITelephony");
                return (ITelephony)(iin != null && iin instanceof ITelephony?(ITelephony)iin:new ITelephony.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _result;
            switch(code) {
                case 1:
                    data.enforceInterface("com.android.internal.telephony.ITelephony");
                    _result = this.endCall();
                    reply.writeNoException();
                    reply.writeInt(_result?1:0);
                    return true;
                case 2:
                    data.enforceInterface("com.android.internal.telephony.ITelephony");
                    this.answerRingingCall();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.android.internal.telephony.ITelephony");
                    _result = this.enableDataConnectivity();
                    reply.writeNoException();
                    reply.writeInt(_result?1:0);
                    return true;
                case 4:
                    data.enforceInterface("com.android.internal.telephony.ITelephony");
                    _result = this.disableDataConnectivity();
                    reply.writeNoException();
                    reply.writeInt(_result?1:0);
                    return true;
                case 5:
                    data.enforceInterface("com.android.internal.telephony.ITelephony");
                    _result = this.isDataConnectivityPossible();
                    reply.writeNoException();
                    reply.writeInt(_result?1:0);
                    return true;
                case 1598968902:
                    reply.writeString("com.android.internal.telephony.ITelephony");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITelephony {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.android.internal.telephony.ITelephony";
            }

            public boolean endCall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void answerRingingCall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public boolean enableDataConnectivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public boolean disableDataConnectivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public boolean isDataConnectivityPossible() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }
        }
    }
}

