package com.barbellnation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRespDTO {
	    private Long inventoryId;
	    private String name;
	    private long price;
	    private String status;
	    private long quantity;
	    private String ownerName;
}
