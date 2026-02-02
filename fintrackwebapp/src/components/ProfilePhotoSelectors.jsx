import { Upload, User, Trash } from 'lucide-react';
import React, { useRef, useState } from 'react';

function ProfilePhotoSelectors({ image, setImage }) {
    const inputRef = useRef(null);
    const [previewUrl, setPreviewUrl] = useState(image);

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setImage(file);
            const preview = URL.createObjectURL(file);
            setPreviewUrl(preview);
        }
    };

    const handleImageRemove = (e) => {
        e.preventDefault();
        setImage(null);
        setPreviewUrl(null);
    };

    const onChooseFile = (e) => {
        e.preventDefault();
        inputRef.current.click();
    };

    return (
        <div className="flex justify-center mb-6">
            <input
                type="file"
                accept="image/*"
                ref={inputRef}
                onChange={handleImageChange}
                className="hidden"
            />

            {!image ? (
                <div
                    onClick={onChooseFile}
                    className="relative w-20 h-20 border-2 bg-purple-100 rounded-full border-none flex items-center justify-center cursor-pointer hover:border-purple-900 transition"
                >
                    <User className="text-purple-500" size={35} />
                    <button className="w-8 h-8 flex items-center justify-center text-white rounded-full border-none absolute -bottom-1 -right-1 bg-purple-50" >
                        <Upload size={15} className='text-purple-600' />
                    </button>
                </div>
            ) : (
                <div className="relative">
                    <img
                        src={previewUrl}
                        alt="Profile"
                        className="w-20 h-20 object-cover rounded-full"
                    />
                    <button
                        onClick={handleImageRemove}
                        className="w-8 h-8 flex items-center justify-center bg-red-800 text-white rounded-full absolute -bottom-1 -right-1"
                    >
                        <Trash size={15} />
                    </button>
                </div>
            )}
        </div>
    );
}

export default ProfilePhotoSelectors;
