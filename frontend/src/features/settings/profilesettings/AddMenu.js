import moment from "moment";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import TitleCard from "../../../components/Cards/TitleCard";
import { showNotification } from '../../common/headerSlice';
import InputText from '../../../components/Input/InputText';
import TextAreaInput from '../../../components/Input/TextAreaInput';
import ToogleInput from '../../../components/Input/ToogleInput';
import AddCategory from "./AddCat";

function AddMenu() {
    const dispatch = useDispatch();

    // Call API to update profile settings changes
    const updateProfile = () => {
        // Dispatch an action to update the restaurant profile
        // You can call your API to update the restaurant information here
        dispatch(showNotification({ message: "Restaurant Profile Updated", status: 1 }));
    };

    const updateFormValue = ({ updateType, value }) => {
        console.log(updateType);
    };

    return (
        <>
            <TitleCard title="Add Menu" topMargin="mt-2">
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <InputText labelTitle="Menu item" defaultValue="My Restaurant" updateFormValue={updateFormValue} />
                    <InputText labelTitle="Name" defaultValue="info@myrestaurant.com" updateFormValue={updateFormValue} />
                    <InputText labelTitle="Description" defaultValue="A great restaurant serving delicious food." updateFormValue={updateFormValue} />
                    <InputText labelTitle="price" defaultValue="California" updateFormValue={updateFormValue} />
                    <TextAreaInput labelTitle="About" defaultValue="Serving the best food in town." updateFormValue={updateFormValue} />
                </div>
                <div className="divider"></div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <InputText labelTitle="Category" defaultValue="Italian" updateFormValue={updateFormValue} />
                    <InputText labelTitle="Rating" defaultValue="4.5" updateFormValue={updateFormValue} />
                    <ToogleInput updateType="syncData" labelTitle="Sync Data" defaultValue={true} updateFormValue={updateFormValue} />
                </div>

                <div className="mt-16">
                    <button className="btn btn-primary float-right" onClick={() => updateProfile()}>
                        Update Profile
                    </button>
                </div>
            </TitleCard>
        </>
    );
}

export default AddMenu;
